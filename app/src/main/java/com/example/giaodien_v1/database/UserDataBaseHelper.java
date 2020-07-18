package com.example.giaodien_v1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.giaodien_v1.model.User;

public class UserDataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "USER.DB";
    private static final String TABLE_NAME = "USER";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String MONEY = "money";
    private static final int VERSION = 1;


    public UserDataBaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + TABLE_NAME + " (id integer primary key autoincrement, email text, user_name text, password text, money long)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "drop table if exists " + TABLE_NAME;
        sqLiteDatabase.execSQL(query);
    }

    public Boolean insert(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL,user.getEmail());
        contentValues.put(USER_NAME,user.getUserName());
        contentValues.put(PASSWORD,user.getPassword());
        contentValues.put(MONEY,user.getMoney());
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }
        return true;
    }

}
