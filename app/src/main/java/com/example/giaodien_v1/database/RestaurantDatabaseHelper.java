package com.example.giaodien_v1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.giaodien_v1.model.Restaurant;

public class RestaurantDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "FOOD.DB";
    private static final String TABLE_NAME = "RESTAURANT";
    private static final String RESTAURANT_NAME = "restaurant_name";
    private static final String V1 = "v1";
    private static final String V2 = "v2";
    private static final String SRC_IMG = "src_img";
    private static final int VERSION = 2;

    private static final int TABLE_DBVERSION = 2;


    public RestaurantDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ex = "create table " + TABLE_NAME + " (restaurant_id integer primary key autoincrement," +
                "restaurant_name TEXT" +
                ",v1 double,v2 double,src_img integer)";
        sqLiteDatabase.execSQL(ex);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Boolean insert(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RESTAURANT_NAME, restaurant.getRestaurant_name());
        contentValues.put(V1, restaurant.getV1());
        contentValues.put(V2, restaurant.getV2());
        contentValues.put(SRC_IMG, restaurant.getSrc_img());
        Long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }
}
