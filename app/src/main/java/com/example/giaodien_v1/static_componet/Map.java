package com.example.giaodien_v1.static_componet;

import com.example.giaodien_v1.R;

public class Map {
    public static int mapping(int imageId){
        switch (imageId){
            case 1: return R.drawable.burrito;
            case 2: return R.drawable.cheeseburger;
            case 3: return R.drawable.coffee_house_icon;
            case 4: return R.drawable.corner;
            case 5: return R.drawable.fried_checken;
            case 6: return R.drawable.hambergur;
            case 7: return R.drawable.milk_shake;
            case 8: return R.drawable.taco;
            case 9: return R.drawable.sandwick;
            case 10: return R.drawable.muffin;
            case 11: return R.drawable.milks_shake;
        }
        return 0;
    }
}
