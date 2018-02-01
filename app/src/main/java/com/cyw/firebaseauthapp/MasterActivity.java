package com.cyw.firebaseauthapp;

import android.*;
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
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

//師父主畫面共六顆按鈕,1.師父基本資料連至(BasicDataMaintain,BasicDataMainPWD)
// 2.訂單連至(OrderActivity,WaitingMoney,OpenOrder,ClosedOrder)
// 3.預約連至
// 4.分店資訊連至
// 5
// 6
public class MasterActivity extends AppCompatActivity implements View.OnClickListener{

    Button masterData,orderData,scheduleData,store,btn5,btn6;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        masterData=(Button)findViewById(R.id.masterData);
        orderData=(Button)findViewById(R.id.orderData);
        scheduleData=(Button)findViewById(R.id.scheduleData);
        store=(Button)findViewById(R.id.store);
        btn5=(Button)findViewById(R.id.btn5);
        btn6=(Button)findViewById(R.id.btn6);
        masterData.setOnClickListener(this);
        orderData.setOnClickListener(this);
        scheduleData.setOnClickListener(this);
        store.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.masterData:
                Intent it1=new Intent(MasterActivity.this,MasterBasicData.class);
                startActivity(it1);
                break;
            case R.id.orderData:
                Intent it2=new Intent(MasterActivity.this,OrderActivity.class);
                startActivity(it2);
                break;
            case R.id.scheduleData:
                Intent it3=new Intent(MasterActivity.this,ScheduleData.class);
                startActivity(it3);
                break;
            case R.id.store:

                Intent it4=new Intent(MasterActivity.this,StoreLoc.class);
                startActivity(it4);
                break;
            case R.id.btn5:
                break;
            case R.id.btn6:
                break;
        }
    }

}
