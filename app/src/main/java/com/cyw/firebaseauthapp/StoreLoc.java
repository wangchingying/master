package com.cyw.firebaseauthapp;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cyw.firebaseauthapp.Store.Store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class StoreLoc extends AppCompatActivity {
    ListView lv1;
    ArrayList<Store> stores;
    Myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_loc);
        //lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        lv1 = (ListView) findViewById(R.id.store_listView);
        adapter = new Myadapter();
        stores = new ArrayList<>();
        stores.add(new Store("林口店", "新北市林口區文化二路一段563-3號1樓", "02 2602 5511", R.drawable.store001));
        stores.add(new Store("中和店", "新北市中和區景新街340號", "02 2602 5511", R.drawable.store002));
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(StoreLoc.this, StoreMap.class);
                String[] fromto = {"您所在的位置", stores.get(i).address};
                it.putExtra("fromto", fromto);
                startActivity(it);
            }
        });
    }


    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return stores.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(StoreLoc.this);
            View v = inflater.inflate(R.layout.myitem_store, null);
            TextView tv = v.findViewById(R.id.store_name);
            TextView tv1 = v.findViewById(R.id.store_addr);
            TextView tv2 = v.findViewById(R.id.store_tel);
            ImageView iv = v.findViewById(R.id.store_pic);
            tv.setText(stores.get(position).storeName);
            tv1.setText(stores.get(position).address);
            tv2.setText(stores.get(position).tel);
            iv.setImageResource(stores.get(position).pic);
            return v;
        }
    }
}

