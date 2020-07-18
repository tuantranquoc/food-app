package com.example.giaodien_v1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.giaodien_v1.model.CardView;
import com.example.giaodien_v1.model.Food;

import java.util.ArrayList;

public class FoodDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "FOOD.DB";
    private static final String TABLE_NAME = "FOOD";
    private static final String FOOD_NAME = "food_name";
    private static final String FOOD_COST = "cost";
    private static final String SRC_IMG = "src_img";

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(VERSION);
    }

    private static final String FOOD_ID = "food_id";
    private static final int VERSION = 1;

    public FoodDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ex = "create table " + TABLE_NAME + " (food_id integer primary key autoincrement,food_name TEXT" +
                ",cost integer,src_img integer)";
        sqLiteDatabase.execSQL(ex);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Boolean insert(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_NAME, food.getFood_name());
        contentValues.put(FOOD_COST, food.getCost());
        contentValues.put(SRC_IMG, food.getSrc_img());
        Long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public ArrayList<CardView> convert(ArrayList<Food> foods) {
        ArrayList<CardView> cardViews = new ArrayList<>();
        for (Food food : foods) {
            cardViews.add(new CardView(food.getFood_name(), food.getSrc_img()));
        }
        return cardViews;
    }

    public ArrayList<CardView> getAllContacts() {
        ArrayList<Food> foods = new ArrayList<>();
        String[] criterial = null;
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
          //      food.setFoodId(Integer.parseInt(cursor.getString(0)));
                food.setFood_name(cursor.getString(1));
                food.setCost(Integer.parseInt(cursor.getString(2)));
                food.setSrc_img(Integer.parseInt(cursor.getString(3)));
                // Add student to list
                foods.add(food);
            } while (cursor.moveToNext());
        }
        return convert(foods);
    }

    public ArrayList<Food> getAllFoods() {
        ArrayList<Food> foods = new ArrayList<>();
        String[] criterial = null;
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setFoodId(Integer.parseInt(cursor.getString(0)));
                food.setFood_name(cursor.getString(1));
                food.setCost(Integer.parseInt(cursor.getString(2)));
                food.setSrc_img(Integer.parseInt(cursor.getString(3)));
                // Add student to list
                foods.add(food);
            } while (cursor.moveToNext());
        }
        return foods;
    }
}
