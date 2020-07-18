package com.example.giaodien_v1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.giaodien_v1.model.Food;

import java.util.ArrayList;

public class FoodRestaurant extends SQLiteOpenHelper {
    private static final String DB_NAME = "FOOD.DB";
    private static final String TABLE_NAME = "FOOD_RESTAURANT";
    private static final String FOOD_ID = "food_id";
    private static final String RESTAURANT_ID = "restaurant_id";
    private static final int VERSION = 3;

    public FoodRestaurant(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ex = "create table " + TABLE_NAME + " (restaurant_id integer,food_id integer, FOREIGN KEY(food_id) REFERENCES FOOD(food_id))";
        sqLiteDatabase.execSQL(ex);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public Boolean insert(int restaurant_id,int food_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RESTAURANT_ID, restaurant_id);
        contentValues.put(FOOD_ID, food_id);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public void insert(int restaurant_id, ArrayList<Food> foods) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (Food food : foods){
            contentValues.clear();
            contentValues.put(RESTAURANT_ID, restaurant_id);
            contentValues.put(FOOD_ID, food.getFoodId());
            db.insert(TABLE_NAME, null, contentValues);
        }
    }
}
