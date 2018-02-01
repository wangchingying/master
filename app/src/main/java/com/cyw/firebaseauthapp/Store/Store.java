package com.cyw.firebaseauthapp.Store;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.cyw.firebaseauthapp.ScheduleData;

/**
 * Created by Student on 2018/2/1.
 */

public class Store {
    public String storeName;
    public String address;
    public String tel;
    public int pic;

    public Store(String storeName,String address, String tel,int pic) {
        this.storeName=storeName;
        this.address=address;
        this.tel=tel;
        this.pic=pic;
    }
}