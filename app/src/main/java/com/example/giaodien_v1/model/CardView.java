package com.example.giaodien_v1.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class CardView implements Parcelable {
    String foodName;
    int foodId;
    int srcImage;


    public CardView(String foodName, int foodId, int srcImage) {
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

    public CardView(String foodName, int srcImage) {
        this.foodName = foodName;
        this.srcImage = srcImage;
    }

    public static Creator<CardView> getCREATOR() {
        return CREATOR;
    }

    public CardView(Food food){
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
        parcel.writeStringArray(new String[]{this.foodName, String.valueOf(this.foodId), String.valueOf(this.srcImage)});
    }

    public CardView(Parcel in) {
        String[] data = new String[3];
        in.readStringArray(data);
        this.foodName = data[0];
        this.foodId = Integer.parseInt(data[1]);
        this.srcImage = Integer.parseInt(data[2]);
    }

    public static final Parcelable.Creator<CardView> CREATOR = new Parcelable.Creator<CardView>() {

        @Override
        public CardView createFromParcel(Parcel parcel) {
            return new CardView(parcel);
        }

        @Override
        public CardView[] newArray(int i) {
            return new CardView[i];
        }
    };
}