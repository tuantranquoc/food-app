package com.example.giaodien_v1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CardView01 implements Parcelable {
    String foodName;
    int foodId;
    int srcImage;


    public CardView01(String foodName, int foodId, int srcImage) {
        this.foodName = foodName;
        this.foodId = foodId;
        this.srcImage = srcImage;
    }


    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(int srcImage) {
        this.srcImage = srcImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public CardView01(String foodName, int srcImage) {
        this.foodName = foodName;
        this.srcImage = srcImage;
    }



    public CardView01(Food food){
        this.foodName = food.getFood_name();
        this.srcImage = food.getSrc_img();
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{this.foodName,String.valueOf(this.foodId), String.valueOf(this.srcImage)});
    }

    public CardView01(Parcel in) {
        String[] data = new String[2];
        in.readStringArray(data);
        this.foodName = data[0];
        this.foodId = Integer.parseInt(data[1]);
        this.srcImage = Integer.parseInt(data[2]);
    }

    public static final Creator<CardView01> CREATOR = new Creator<CardView01>() {

        @Override
        public CardView01 createFromParcel(Parcel parcel) {
            return new CardView01(parcel);
        }

        @Override
        public CardView01[] newArray(int i) {
            return new CardView01[i];
        }
    };
}