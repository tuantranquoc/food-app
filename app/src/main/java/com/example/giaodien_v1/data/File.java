package com.example.giaodien_v1.data;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;


import com.example.giaodien_v1.model.CardView;
import com.example.giaodien_v1.model.User;
import com.example.giaodien_v1.static_componet.Map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class File {
    public static void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    public static String readFromFile(Context context) {

        String ret = "";
        String result = "";
        String[] str = null;
        try {
            InputStream inputStream = context.openFileInput("data.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                    str = receiveString.split(",");
                    for (String a:str
                         ) {
                            result += (" " + a);
                    }
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return result;
    }

    public static ArrayList<User> getUserDataFromFile(Context context){
        String[] str = null;
        AssetManager as = context.getAssets();
        ArrayList<User> users = new ArrayList<>();
        try {
       //     InputStream inputStream = context.openFileInput("data.txt");
            InputStream inputStream = as.open("data.txt");
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    str = receiveString.split(",");
                    users.add(new User(str[1].toString(),str[2].toString(),str[3].toString(),Integer.parseInt(str[4])));
                }
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return users;
    }



    public static ArrayList<CardView> getCardViewData(Context context){
        String[] str = null;
        AssetManager as = context.getAssets();
        ArrayList<CardView> cardViews = new ArrayList<>();
        try {
            //     InputStream inputStream = context.openFileInput("data.txt");
            InputStream inputStream = as.open("restaurant_data.txt");
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    str = receiveString.split(",");
                    cardViews.add(new CardView(str[0], Map.mapping(Integer.parseInt(str[1]))));
                }
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return cardViews;
    }
}
