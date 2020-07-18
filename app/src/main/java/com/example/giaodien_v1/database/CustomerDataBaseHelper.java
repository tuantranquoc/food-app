package com.example.giaodien_v1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.giaodien_v1.auth.Auth;

public class CustomerDataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "FOOD.DB";
    private static final String TABLE_NAME = "CUSTOMER";
    private static final String EMAIL = "email";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";
    private static final String FUND = "fund";
    private static final String ID = "id";
    private static final int VERSION = 1;


    public CustomerDataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ex = "create table " + TABLE_NAME + " (id integer primary key autoincrement,email TEXT" +
                ",user_name TEXT,password TEXT,fund integer)";
        sqLiteDatabase.execSQL(ex);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Boolean insert(String email,String userName,String password,int fund){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL,email);
        contentValues.put(USER_NAME,userName);
        contentValues.put(PASSWORD,password);
        contentValues.put(FUND,fund);
        Long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1 ){
            return false;
        }
        return true;
    }

    public Boolean Login(String userName, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,"user_name=? AND password=?",
                new String[]{userName,password},null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            Auth.userId = cursor.getString(1);
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public int getUserId(String userName, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select id from " + TABLE_NAME + " where username=" + userName + " password="
                + password;
        return 0;
    }
}
