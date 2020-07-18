package com.example.giaodien_v1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.giaodien_v1.R;
import com.example.giaodien_v1.auth.Session;
import com.example.giaodien_v1.model.Bill;
import com.example.giaodien_v1.model.CardView;
import com.example.giaodien_v1.model.Comment;
import com.example.giaodien_v1.model.Customer;
import com.example.giaodien_v1.model.Display;
import com.example.giaodien_v1.model.Favorite;
import com.example.giaodien_v1.model.Food;
import com.example.giaodien_v1.model.FoodBill;
import com.example.giaodien_v1.model.FoodTag;
import com.example.giaodien_v1.model.Location;
import com.example.giaodien_v1.model.Restaurant;
import com.example.giaodien_v1.model.ShoppingCart;
import com.example.giaodien_v1.model.Tag;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "DATA.DB";
    private static final String TABLE_CUSTOMER = "CUSTOMER";
    private static final String EMAIL = "email";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";
    private static final String FUND = "fund";
    private static final String ID = "id";

    private static final String TABLE_FOOD = "FOOD";
    private static final String FOOD_NAME = "food_name";
    private static final String FOOD_COST = "cost";
    private static final String SRC_IMG = "src_img";

    private static final String TABLE_RESTAURANT = "RESTAURANT";
    private static final String RESTAURANT_NAME = "restaurant_name";
    private static final String V1 = "v1";
    private static final String V2 = "v2";

    private static final String TABLE_FRESTAURANT = "FOOD_RESTAURANT";
    private static final String FOOD_ID = "food_id";
    private static final String RESTAURANT_ID = "restaurant_id";

    private static final String TABLE_TAG = "TAG";
    private static final String TAG_ID = "tag_id";
    private static final String TAG_NAME = "tag_name";

    private static final String TABLE_FOOD_TAG = "FOOD_TAG";

    private static final String TABLE_SHOPPING_CART = "SHOPPING_CART";
    private static final String SHOOPING_CART_ID = "shopping_cart_id";
    private static final String USER_ID = "user_id";
    private static final String NUMBER = "number";
    private static final String TOTAL_COST = "total_cost";


    private static final String TABLE_BILL = "BILL";
    private static final String BILL_ID = "bill_id";
    private static final String STATUS = "status";

    private static final String TABLE_FOOD_BILL = "FOOD_BILL";

    private static final String TABLE_FAVORITE = "FAVORITE";
    private static final String FAVORITE_ID = "favorite_id";

    private static final String TABLE_COMMENT = "COMMENT";
    private static final String COMMENT_ID = "comment_id";
    private static final String COMMENT = "comment";

    private static final String TABLE_DELIVERY = "DELIVERY";

    private static final String TABLE_ROLE = "ROLE";
    private static final String ROLE_ID = "role_id";
    private static final String ROLE_NAME = "role_name";

    private static final String TABLE_USER_ROLE = "USER_ROLE";

    private static final String TABLE_LOCATION = "LOCATION";
    private static final String LOCATION_ID = "location_id";

    private static final String PAID = "paid";
    private static final String PROCESS = "process";
    private static final String YET = "yet";

    private static final int VERSION = 2;

    Session session;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        SQLiteDatabase database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ex = "create table " + TABLE_FOOD + " (food_id integer primary key autoincrement,food_name TEXT" +
                ",cost integer,src_img integer)";
        String ex1 = "create table " + TABLE_CUSTOMER + " (id integer primary key autoincrement,email TEXT" +
                ",user_name TEXT,password TEXT,fund integer)";
        String ex2 = "create table " + TABLE_RESTAURANT + " (restaurant_id integer primary key autoincrement," +
                "restaurant_name TEXT" +
                ",v1 double,v2 double,src_img integer)";
        String ex3 = "create table " + TABLE_FRESTAURANT + " (restaurant_id integer,food_id integer, FOREIGN KEY(food_id) REFERENCES FOOD(food_id)," +
                " FOREIGN KEY(restaurant_id) REFERENCES RESTAURANT(restaurant_id))";
        String ex4 = "create table " + TABLE_TAG + " (tag_id integer primary key autoincrement,tag_name TEXT" +
                ")";
        String ex5 = "create table " + TABLE_FOOD_TAG + " (food_id integer ,tag_id integer" +
                ",FOREIGN KEY(food_id) REFERENCES FOOD(food_id), FOREIGN KEY(tag_id) REFERENCES TAG(tag_id))";
        String ex6 = "create table " + TABLE_SHOPPING_CART + " (shopping_cart_id integer primary key autoincrement,user_id integer" +
                ",food_id integer,number integer,cost integer,total_cost integer)";
        String ex7 = "create table " + TABLE_BILL + " (bill_id integer primary key autoincrement,user_id integer" +
                ",total_cost integer,status TEXT)";
        String ex8 = "create table " + TABLE_FOOD_BILL + " (bill_id integer ,food_id integer,number Ftableinteger)";
        String ex9 = "create table " + TABLE_FAVORITE + " (favorite_id integer primary key autoincrement,user_id integer,food_id integer,food_name TEXT" +
                ",src_img integer)";
        String ex10 = "create table " + TABLE_COMMENT + " (comment_id integer primary key autoincrement,user_id integer,food_id integer" +
                ",comment TEXT)";
        String ex11 = "create table " + TABLE_DELIVERY + " (user_id integer primary key,user_name TEXT,role integer)";

        String ex12 = "create table " + TABLE_ROLE + " (role_id integer primary key,role_name TEXT)";
        String ex13 = "create table " + TABLE_USER_ROLE + " (user_id integer,role_id)";

        String ex14 = "create table " + TABLE_LOCATION + " (location_id integer primary key autoincrement,bill_id integer,user_id integer,v1 double,v2 double)";


        sqLiteDatabase.execSQL(ex);
        sqLiteDatabase.execSQL(ex1);
        sqLiteDatabase.execSQL(ex2);
        sqLiteDatabase.execSQL(ex3);
        sqLiteDatabase.execSQL(ex4);
        sqLiteDatabase.execSQL(ex5);
        sqLiteDatabase.execSQL(ex6);
        sqLiteDatabase.execSQL(ex7);
        sqLiteDatabase.execSQL(ex8);
        sqLiteDatabase.execSQL(ex9);
        sqLiteDatabase.execSQL(ex10);
        sqLiteDatabase.execSQL(ex11);
        sqLiteDatabase.execSQL(ex12);
        sqLiteDatabase.execSQL(ex13);
        sqLiteDatabase.execSQL(ex14);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FRESTAURANT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD_TAG);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING_CART);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BILL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DELIVERY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ROLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);

        onCreate(sqLiteDatabase);
    }

    // TODO INSERT INSTANCE
    public void insert(DatabaseHelper databaseHelper) {
        databaseHelper.insert(new Food("cheeseburger", 100, R.drawable.cheeseburger));
        databaseHelper.insert(new Food("corner", 100, R.drawable.white_cup_with_black_coffee));
        databaseHelper.insert(new Food("fried_checken", 100, R.drawable.fried_checken));
        databaseHelper.insert(new Food("hamburger", 100, R.drawable.hambergur));
        databaseHelper.insert(new Food("taco", 100, R.drawable.taco));
        databaseHelper.insert(new Food("sandwick", 100, R.drawable.sandwick));
        databaseHelper.insert(new Food("milks_shake", 100, R.drawable.milks_shake));
        databaseHelper.insert(new Food("muffin", 100, R.drawable.muffin));

        databaseHelper.insert(new Restaurant("Xuan Ha", 10.7969738, 106.6703815, R.drawable.store_xuanla_hn_master));

        databaseHelper.insert(1, databaseHelper.getAllFoods());
        databaseHelper.insert("tuan@gmail.com", "tuan", "password", 1000);
        databaseHelper.insert("bao@gmail.com", "bao", "password", 1000);
        databaseHelper.insert(10, "bao", 1);
        databaseHelper.insert(0, "CUSTOMER");
        databaseHelper.insert(1, "DELIVERY");

        databaseHelper.insertUR(0, 1);
        databaseHelper.insertUR(1, 2);


        databaseHelper.insert(new Tag("fast food"));
        databaseHelper.insert(new Tag("noodle"));
        databaseHelper.insert(new Tag("coffee"));
        databaseHelper.insert(new Tag("cake"));
        databaseHelper.insert(new Tag("cold"));

        databaseHelper.insert(new FoodTag(1, 1));
        databaseHelper.insert(new FoodTag(2, 1));
        databaseHelper.insert(new FoodTag(3, 1));
        databaseHelper.insert(new FoodTag(4, 1));
        databaseHelper.insert(new FoodTag(5, 1));
        databaseHelper.insert(new FoodTag(6, 1));
        databaseHelper.insert(new FoodTag(7, 4));
        databaseHelper.insert(new FoodTag(8, 5));
        databaseHelper.insert(new FoodTag(9, 3));
        databaseHelper.insert(new FoodTag(5, 2));
    }

    // TODO CUSTOMER TABLE METHOD
    public Boolean insert(String email, String userName, String password, int fund) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL, email);
        contentValues.put(USER_NAME, userName);
        contentValues.put(PASSWORD, password);
        contentValues.put(FUND, fund);
        Long result = db.insert(TABLE_CUSTOMER, null, contentValues);


        contentValues.clear();
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        cursor.moveToLast();
        int user_id = Integer.parseInt(cursor.getString(0));
        contentValues.put(USER_ID,user_id);
        contentValues.put(ROLE_ID,0);
        Long result01 = db.insert(TABLE_USER_ROLE, null, contentValues);

        if (result == -1) {
            return false;
        }
        return true;
    }

    public Customer login(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Customer customer;
        Cursor cursor = db.query(TABLE_CUSTOMER, null, "email=? AND password=?",
                new String[]{email, password}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            customer = new Customer();
            customer.setId(Integer.parseInt(cursor.getString(0)));
            customer.setEmail(cursor.getString(1));
            customer.setUserName(cursor.getString(2));
            cursor.close();
            db.close();
            return customer;
        }
        cursor.close();
        db.close();
        return null;
    }

    public int getFund(int user_id){
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER + " WHERE " + ID + " = " + user_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        cursor.moveToFirst();
       return Integer.parseInt(cursor.getString(4));

    }

    public void updateFund(int user_id, int fund){
        Customer customer = getUserByUserId(user_id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, customer.getId());
        contentValues.put(EMAIL, customer.getEmail());
        contentValues.put(PASSWORD, customer.getPassword());
        contentValues.put(FUND, fund);
        contentValues.put(USER_NAME, customer.getUserName());

        db.update(TABLE_CUSTOMER,contentValues,"id=?",new String[]{String.valueOf(user_id)});
    }

    public Customer getUserByUserId(int user_id){
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER + " WHERE " + ID + " = " + user_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        cursor.moveToFirst();
        Customer customer = new Customer();
        customer.setId(user_id);
        customer.setUserName(cursor.getString(2));
        customer.setEmail(cursor.getString(1));
        customer.setPassword(cursor.getString(3));
        customer.setFund(cursor.getShort(4));
        return customer;
    }

    public Boolean loginByEmail(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Customer customer;
        Cursor cursor = db.query(TABLE_CUSTOMER, null, "email=? AND password=?",
                new String[]{email, password}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public Boolean loginByEmailChange(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Customer customer;
        Cursor cursor = db.query(TABLE_DELIVERY, null, "email=? AND password=?",
                new String[]{email, password}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    // TODO FOOD TABLE METHOD
    public int findSrcById(int food_id) {
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_ID + " = " + food_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        cursor.moveToFirst();
        int srcImg = Integer.parseInt(cursor.getString(3));
        return srcImg;
    }

    public Boolean insert(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_NAME, food.getFood_name());
        contentValues.put(FOOD_COST, food.getCost());
        contentValues.put(SRC_IMG, food.getSrc_img());
        contentValues.put(SRC_IMG, food.getSrc_img());
        Long result = db.insert(TABLE_FOOD, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public Boolean insert(int role_id, String role_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROLE_ID, role_id);
        contentValues.put(ROLE_NAME, role_name);
        Long result = db.insert(TABLE_ROLE, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public Boolean insertUR(int role_id, int user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROLE_ID, role_id);
        contentValues.put(USER_ID, user_id);
        Long result = db.insert(TABLE_USER_ROLE, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public int findCostByFoodId(int food_id) {
        int cost = 0;
        String[] criterial = null;
        String selectQuery = "SELECT cost FROM " + TABLE_FOOD + " WHERE " + FOOD_ID + " = " + food_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        cursor.moveToFirst();
        cost = Integer.parseInt(cursor.getString(0));
        return cost;
    }

    public String findNameByFoodId(int food_id) {
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_ID + " = " + food_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        cursor.moveToFirst();
        return cursor.getString(1);
    }

    public ArrayList<CardView> convert(ArrayList<Food> foods) {
        ArrayList<CardView> cardViews = new ArrayList<>();
        for (Food food : foods) {
            cardViews.add(new CardView(food.getFood_name(), food.getFoodId(), food.getSrc_img()));
        }
        return cardViews;
    }

    public ArrayList<CardView> getAllContacts() {
        ArrayList<Food> foods = new ArrayList<>();
        String[] criterial = null;
        //  String selectQuery = "SELECT  * FROM " + TABLE_FOOD;
        //  String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_ID + " = 1 AND " + KEY_PASS + " = ?";
        String selectQuery = "SELECT * FROM " + TABLE_FOOD;
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
        return convert(foods);
    }

    public ArrayList<Food> getAllFoods() {
        ArrayList<Food> foods = new ArrayList<>();
        String[] criterial = null;
        String selectQuery = "SELECT  * FROM " + TABLE_FOOD;
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


    public Food findFoodByFoodId(int food_id){
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_ID + " = " + food_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        cursor.moveToFirst();
        Food food = new Food();
        food.setFoodId(Integer.parseInt(cursor.getString(0)));
        food.setFood_name(cursor.getString(1));
        food.setCost(Integer.parseInt(cursor.getString(2)));
        food.setSrc_img(Integer.parseInt(cursor.getString(3)));
        return food;
    }
    public ArrayList<CardView> getAllFoodByTag(int tag_id) {
        ArrayList<Food> foods = new ArrayList<>();
        String[] criterial = null;
        //       private final String MY_QUERY = "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";

        //     db.rawQuery(MY_QUERY, new String[]{String.valueOf(propertyId)});
//        String selectQuery = "SELECT * FROM " + TABLE_TAG + " WHERE " + TAG_ID + " = " + tag_id;

        String selectQeery = "SELECT * FROM " + TABLE_FOOD_TAG + " a INNER JOIN " + TABLE_FOOD +
                " b ON a.food_id=b.food_id WHERE a.tag_id=?";
        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor cursor = db.rawQuery(selectQeery, criterial);
        Cursor cursor = db.rawQuery(selectQeery, new String[]{String.valueOf(tag_id)});
        if (cursor.moveToFirst()) {
            do {
                Food food = new Food(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(3), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));
                foods.add(food);
            } while (cursor.moveToNext());
        }
        return convert(foods);
    }

    // TODO RESTAURANT TABLE METHOD
    public Boolean insert(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RESTAURANT_NAME, restaurant.getRestaurant_name());
        contentValues.put(V1, restaurant.getV1());
        contentValues.put(V2, restaurant.getV2());
        contentValues.put(SRC_IMG, restaurant.getSrc_img());
        Long result = db.insert(TABLE_RESTAURANT, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    // TODO FOOD RESTAURANT TABLE METHOD
    public Boolean insert(int restaurant_id, int food_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RESTAURANT_ID, restaurant_id);
        contentValues.put(FOOD_ID, food_id);
        long result = db.insert(TABLE_FRESTAURANT, null, contentValues);
        return result != -1;
    }

    public void insert(int restaurant_id, ArrayList<Food> foods) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (Food food : foods) {
            contentValues.clear();
            contentValues.put(RESTAURANT_ID, restaurant_id);
            contentValues.put(FOOD_ID, food.getFoodId());
            db.insert(TABLE_FRESTAURANT, null, contentValues);
        }
    }

    // TODO TABLE TAG METHOD
    public Boolean insert(Tag tag) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TAG_NAME, tag.getTag_name());
        Long result = db.insert(TABLE_TAG, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public String getFoodTagByTagId(int tag_id) {
        String tag = null;
        String[] criterial = null;
        //  String selectQuery = "SELECT  * FROM " + TABLE_FOOD;
        //  String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_ID + " = ? AND " + KEY_PASS + " = ?";
        String selectQuery = "SELECT * FROM " + TABLE_TAG + " WHERE " + TAG_ID + " = " + tag_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        if (cursor.moveToFirst()) {
            do {
                tag = cursor.getString(1);
            } while (cursor.moveToNext());
        }
        return tag;
    }

    // TODO TABLE FOOD TAG METHOD
    public Boolean insert(FoodTag foodTag) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_ID, foodTag.getFood_id());
        contentValues.put(TAG_ID, foodTag.getTag_id());
        Long result = db.insert(TABLE_FOOD_TAG, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public ArrayList<CardView> findFoodByName(String food_name) {
        ArrayList<Food> foods = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] criterial = null;
        //  String selectQuery = "SELECT  * FROM " + TABLE_FOOD;
        //  String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_ID + " = ? AND " + KEY_PASS + " = ?";
        //      String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_NAME + " = " + food_name;
        String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_NAME + "=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{food_name});

        //  Cursor cursor = db.rawQuery(selectQuery, criterial);
        if (cursor.moveToFirst()) {
            do {
                foods.add(new Food(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3))));
            } while (cursor.moveToNext());
        }
        return convert(foods);
    }
    public ArrayList<CardView> findFoodByTag(String tag) {
        ArrayList<Food> foods = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] criterial = null;
        //  String selectQuery = "SELECT  * FROM " + TABLE_FOOD;
        //  String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_ID + " = ? AND " + KEY_PASS + " = ?";
        //      String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_NAME + " = " + food_name;
        String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + tag + "=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{tag});

        //  Cursor cursor = db.rawQuery(selectQuery, criterial);
        if (cursor.moveToFirst()) {
            do {
                foods.add(new Food(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3))));
            } while (cursor.moveToNext());
        }
        return convert(foods);
    }

    public ArrayList<String> getAllFoodTagByFoodId(int food_id) {
        ArrayList<String> tags = new ArrayList<>();
        String[] criterial = null;
        //  String selectQuery = "SELECT  * FROM " + TABLE_FOOD;
        //  String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_ID + " = ? AND " + KEY_PASS + " = ?";
        String selectQuery = "SELECT * FROM " + TABLE_FOOD_TAG + " WHERE " + FOOD_ID + " = " + food_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        if (cursor.moveToFirst()) {
            do {
                tags.add(getFoodTagByTagId(Integer.parseInt(cursor.getString(1))));
            } while (cursor.moveToNext());
        }
        return tags;
    }

    public ArrayList<CardView> getAllFoodByTagName(String tag_name) {
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<Food> foods = new ArrayList<>();
        String[] criterial = null;
        SQLiteDatabase db = this.getReadableDatabase();
        //  String selectQuery = "SELECT  * FROM " + TABLE_FOOD;
        //  String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_ID + " = ? AND " + KEY_PASS + " = ?";
        String selectQuery = "SELECT * FROM " + TABLE_TAG + " WHERE " + TAG_NAME + "=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{tag_name});
        cursor.moveToFirst();
        int tag_id = Integer.parseInt(cursor.getString(0));
        String selectQuery1 = "SELECT * FROM " + TABLE_FOOD_TAG + " WHERE " + TAG_ID + " = " + tag_id;
        Cursor cursor1 = db.rawQuery(selectQuery1, criterial);
        ArrayList<FoodTag> foodTags = new ArrayList<>();
        if (cursor1.moveToFirst()) {
            do {
                foodTags.add(new FoodTag(Integer.parseInt(cursor1.getString(0)),Integer.parseInt(cursor1.getString(1))));
                Food food = findFoodByFoodId(Integer.parseInt(cursor1.getString(0)));
                foods.add(food);
            } while (cursor.moveToNext());
        }
        return convert(foods);
    }

    public int getTagId(String tag_name) {
        int tag_id = -1;
        String[] criterial = null;
        //  String selectQuery = "SELECT  * FROM " + TABLE_FOOD;
        //  String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_ID + " = ? AND " + KEY_PASS + " = ?";
        String selectQuery = "SELECT * FROM " + TABLE_TAG + " WHERE " + TAG_NAME + " = " + tag_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        if (cursor.moveToFirst()) {
            tag_id = Integer.parseInt(cursor.getString(0));
        }
        return tag_id;
    }

    // TODO TABLE SHOPPING CART TABLE


    public void updateShoppingCart(ShoppingCart shoppingCart){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, shoppingCart.getUserId());
        contentValues.put(FOOD_ID, shoppingCart.getFood_id());
        contentValues.put(NUMBER, shoppingCart.getNumber());
        contentValues.put(FOOD_COST, shoppingCart.getCost());
        contentValues.put(TOTAL_COST, shoppingCart.getNumber()*shoppingCart.getCost());

        db.update(TABLE_SHOPPING_CART,contentValues,"shopping_cart_id=?",new String[]{String.valueOf(shoppingCart.getShoppingCartId())});

    }
    public Boolean insert(ShoppingCart shoppingCart) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, shoppingCart.getUserId());
        contentValues.put(FOOD_ID, shoppingCart.getFood_id());
        contentValues.put(NUMBER, shoppingCart.getNumber());
        contentValues.put(FOOD_COST, shoppingCart.getCost());
        contentValues.put(TOTAL_COST, shoppingCart.getTotal_cost());
        Long result = db.insert(TABLE_SHOPPING_CART, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public ArrayList<ShoppingCart> findAllShoppingCartByUserId(int userId) {
        ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();
        String[] criterial = null;
        //  String selectQuery = "SELECT  * FROM " + TABLE_FOOD;
        //  String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_ID + " = ? AND " + KEY_PASS + " = ?";
        String selectQuery = "SELECT * FROM " + TABLE_SHOPPING_CART + " WHERE " + USER_ID + "=?";
        //    private final String MY_QUERY = "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(userId)});

        //   Cursor cursor = db.rawQuery(selectQuery, criterial);
        if (cursor.moveToFirst()) {
            do {
                ShoppingCart shoppingCart = new ShoppingCart(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1))
                        , Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4))
                        , Integer.parseInt(cursor.getString(5)));
                shoppingCarts.add(shoppingCart);
            } while (cursor.moveToNext());
        }
        return shoppingCarts;
    }

    public boolean remove(int shoppingCartId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_SHOPPING_CART, SHOOPING_CART_ID + "=" + shoppingCartId, null) > 0;
    }


    // TODO BILL TABLE METHOD

    public Boolean insert(ArrayList<ShoppingCart> shoppingCarts) {
        SQLiteDatabase db = this.getWritableDatabase();
        int totalCost = 0;
        ContentValues contentValues = new ContentValues();
        for (ShoppingCart shoppingCart : shoppingCarts) {
            totalCost += shoppingCart.getTotal_cost();
        }
        if(shoppingCarts.size()!=0){
            contentValues.put(USER_ID, shoppingCarts.get(0).getUserId());
            contentValues.put(TOTAL_COST, totalCost);
            contentValues.put(STATUS, YET);
            Long result = db.insert(TABLE_BILL, null, contentValues);
            if (result == -1) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void changeToProcess(Bill bill){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, bill.getUser_id());
        contentValues.put(BILL_ID, bill.getBill_id());
        contentValues.put(STATUS, PROCESS);
        contentValues.put(TOTAL_COST, bill.getTotal_cost());
        db.update(TABLE_BILL,contentValues,"bill_id=?",new String[]{String.valueOf(bill.getBill_id())});
    }

    public void changeToPaid(Bill bill){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, bill.getUser_id());
        contentValues.put(BILL_ID, bill.getBill_id());
        contentValues.put(STATUS, PAID);
        contentValues.put(TOTAL_COST, bill.getTotal_cost());
        db.update(TABLE_BILL,contentValues,"bill_id=?",new String[]{String.valueOf(bill.getBill_id())});
    }

    public int getBillId(int userId) {

        ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();
        String[] criterial = null;
        //  String selectQuery = "SELECT  * FROM " + TABLE_FOOD;
        //  String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_ID + " = ? AND " + KEY_PASS + " = ?";
        String selectQuery = "SELECT * FROM " + TABLE_BILL + " WHERE " + USER_ID + "=?";
        //    private final String MY_QUERY = "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(userId)});
        cursor.moveToLast();
        return Integer.parseInt(cursor.getString(0));
    }

    public ArrayList<Bill> getAllBill() {
        ArrayList<Bill> bills = new ArrayList<>();
        String[] criterial = null;
        String selectQuery = "SELECT  * FROM " + TABLE_BILL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        if (cursor.moveToFirst()) {
            do {
                Bill bill = new Bill();
                bill.setBill_id(Integer.parseInt(cursor.getString(0)));
                bill.setUser_id(Integer.parseInt(cursor.getString(1)));
                bill.setTotal_cost(Integer.parseInt(cursor.getString(2)));
                bill.setStatus(cursor.getString(3));
                bills.add(bill);
                // Add student to list
            } while (cursor.moveToNext());
        }
        return bills;
    }

    public ArrayList<Bill> getAllBillByBillId(int user_id) {
        ArrayList<Bill> bills = new ArrayList<>();
        String[] criterial = null;
        String selectQuery = "SELECT  * FROM " + TABLE_BILL + " WHERE " + USER_ID + "=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(user_id)});
        if (cursor.moveToFirst()) {
            do {
                Bill bill = new Bill();
                bill.setBill_id(Integer.parseInt(cursor.getString(0)));
                bill.setUser_id(Integer.parseInt(cursor.getString(1)));
                bill.setTotal_cost(Integer.parseInt(cursor.getString(2)));
                bill.setStatus(cursor.getString(3));
                bills.add(bill);
                // Add student to list
            } while (cursor.moveToNext());
        }
        return bills;

    }

    // TODO FOOD BILL TABLE METHOD
    public void insertFoodBill(ArrayList<ShoppingCart> shoppingCarts, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int billId = getBillId(userId);
        for (ShoppingCart shoppingCart : shoppingCarts) {
            contentValues.put(BILL_ID, billId);
            contentValues.put(FOOD_ID, shoppingCart.getFood_id());
            contentValues.put(NUMBER, shoppingCart.getNumber());
            db.insert(TABLE_FOOD_BILL, null, contentValues);
            contentValues.clear();
        }
    }

    public boolean removeListSP(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_SHOPPING_CART, userId + "=" + userId, null) > 0;
    }

    // TODO TABLE FAVORITE

    public Boolean insertFavorite(CardView cardView, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, userId);
        contentValues.put(FOOD_NAME, cardView.getFoodName());
        contentValues.put(FOOD_ID, cardView.getFoodId());
        contentValues.put(SRC_IMG, cardView.getSrcImage());
        Long result = db.insert(TABLE_FAVORITE, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

    public ArrayList<CardView> getAllFavorites(int userId) {
        ArrayList<Favorite> favorites = new ArrayList<>();
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_FAVORITE + " WHERE " + USER_ID + "=?";
        //    private final String MY_QUERY = "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            do {
                Favorite favorite = new Favorite();
                favorite.setFavorite_id(Integer.parseInt(cursor.getString(0)));
                favorite.setUser_id(Integer.parseInt(cursor.getString(1)));
                favorite.setFood_id(Integer.parseInt(cursor.getString(2)));
                favorite.setFood_name(cursor.getString(3));
                favorite.setSrc_img(Integer.parseInt(cursor.getString(4)));

                // Add student to list
                favorites.add(favorite);
            } while (cursor.moveToNext());
        }
        return convertFavorite(favorites);
    }

    public ArrayList<CardView> convertFavorite(ArrayList<Favorite> favorites) {
        ArrayList<CardView> cardViews = new ArrayList<>();
        for (Favorite favorite : favorites) {
            cardViews.add(new CardView(favorite.getFood_name(), favorite.getFood_id(), favorite.getSrc_img()));
        }
        return cardViews;
    }

    //TODO TABLE COMMENT
    public void insertCommnet(int userId, int food_id, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, userId);
        contentValues.put(FOOD_ID, food_id);
        contentValues.put(COMMENT, comment);
        db.insert(TABLE_COMMENT, null, contentValues);
    }

    public ArrayList<Comment> getAllComments() {
        ArrayList<Comment> comments = new ArrayList<>();
        String[] criterial = null;
        String selectQuery = "SELECT  * FROM " + TABLE_COMMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        if (cursor.moveToFirst()) {
            do {
                Comment comment = new Comment();
                comment.setComment_id(Integer.parseInt(cursor.getString(0)));
                comment.setUser_id(Integer.parseInt(cursor.getString(1)));
                comment.setFood_id(Integer.parseInt(cursor.getString(2)));
                comment.setComment(cursor.getString(3));

                // Add student to list
                comments.add(comment);
            } while (cursor.moveToNext());
        }
        return comments;
    }

    public ArrayList<Comment> getAllCommentByFoodId(int food_id) {
        ArrayList<Comment> comments = new ArrayList<>();
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_COMMENT + " WHERE " + FOOD_ID + "=?";
        //    private final String MY_QUERY = "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(food_id)});
        if (cursor.moveToFirst()) {
            do {
                Comment comment = new Comment();
                comment.setComment_id(Integer.parseInt(cursor.getString(0)));
                comment.setUser_id(Integer.parseInt(cursor.getString(1)));
                comment.setFood_id(Integer.parseInt(cursor.getString(2)));
                comment.setComment(cursor.getString(3));

                // Add student to list
                comments.add(comment);
            } while (cursor.moveToNext());
        }
        return comments;
    }

    // TODO TABLE DELIVERY
    public void insert(int userId, String user_name, int role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, userId);
        contentValues.put(USER_NAME, user_name);
        contentValues.put("ROLE", role);
        db.insert(TABLE_COMMENT, null, contentValues);
    }

    // TODO TABLE ROLE
    public int getRoleByUserId(int user_id) {
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_USER_ROLE + " WHERE " + USER_ID + " = " + user_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        cursor.moveToFirst();
        return Integer.parseInt(cursor.getString(1));
    }

    public String findUsernameByUserId(int user_id) {
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER + " WHERE " + ID + " = " + user_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        cursor.moveToFirst();
        return cursor.getString(2);
    }

    public String findUsermailByUserId(int user_id) {
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER + " WHERE " + ID + " = " + user_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        cursor.moveToFirst();
        return cursor.getString(1);
    }


    public ArrayList<Display> findAllDisplay(int bill_id) {
        ArrayList<FoodBill> foodBills = new ArrayList<>();
        ArrayList<Display> displays = new ArrayList<>();
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_FOOD_BILL + " WHERE " + BILL_ID + "=?";
        //    private final String MY_QUERY = "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(bill_id)});
        if (cursor.moveToFirst()) {
            do {
                FoodBill foodBill = new FoodBill();
                foodBill.setBill_id(Integer.parseInt(cursor.getString(0)));
                foodBill.setFood_id(Integer.parseInt(cursor.getString(1)));
                foodBill.setNumber(Integer.parseInt(cursor.getString(2)));


                // Add student to list
                foodBills.add(foodBill);
            } while (cursor.moveToNext());
        }

        for (FoodBill f: foodBills) {
            Display display = new Display();
            display.setFoodName(findNameByFoodId(f.getFood_id()));
            display.setNumber(f.getNumber());
            display.setTotalCost(findCostByFoodId(f.getFood_id())*f.getNumber());
            displays.add(display);
        }
        return displays;
    }

    //TODO TABLE LOCATION
    public void insert(int userId, double v1, double v2) {
        String[] criterial = null;
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_BILL;
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        cursor.moveToLast();
        int bill_id = Integer.parseInt(cursor.getString(0));
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, userId);
        contentValues.put(BILL_ID, bill_id);
        contentValues.put(V1, v1);
        contentValues.put(V2, v2);
        db.insert(TABLE_LOCATION, null, contentValues);
    }
    public Location findLocationByBillId(int bill_id) {
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_LOCATION + " WHERE " + BILL_ID + " = " + bill_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, criterial);
        cursor.moveToFirst();
        Location location = new Location();
        location.setV1(Double.parseDouble(cursor.getString(3)));
        location.setV2(Double.parseDouble(cursor.getString(4)));
        return location;
    }

}
