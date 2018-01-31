package com.cyw.firebaseauthapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cyw.firebaseauthapp.Data.master;
import com.cyw.firebaseauthapp.OrderData.order;
import com.cyw.firebaseauthapp.VIPData.VIP;

public class ClosedOrder_detail extends AppCompatActivity {
    TextView orderId_oo, masterName_oo, store_oo, program_oo, price_oo, transferTime_oo, VIPId_oo, VIPName_oo, balanceTimes_oo, serviceTimes_oo;
    String OID, MID;
    order o;
    master m;
    VIP v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_order_detail);
        orderId_oo = (TextView) findViewById(R.id.orderId_oo);
        masterName_oo = (TextView) findViewById(R.id.masterName_oo);
        store_oo = (TextView) findViewById(R.id.store_oo);
        program_oo = (TextView) findViewById(R.id.program_oo);
        ;
        price_oo = (TextView) findViewById(R.id.price_oo);
        transferTime_oo = (TextView) findViewById(R.id.transferTime_oo);
        VIPId_oo = (TextView) findViewById(R.id.VIPId_oo);
        VIPName_oo = (TextView) findViewById(R.id.VIPName_oo);
        balanceTimes_oo = (TextView) findViewById(R.id.balanceTimes_oo);
        serviceTimes_oo = (TextView) findViewById(R.id.serviceTimes_oo);

        OID = getIntent().getStringExtra("OrderID");
        o = MainActivity.dao_o.getOrder(OID);
        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        MID = sp.getString("id", "");
        m = MainActivity.dao_m.getMaster(MID);
        v = MainActivity.dao_v.getVIP(o.customerId);

        orderId_oo.setText(OID);
        masterName_oo.setText(m.name.toString());
        store_oo.setText(m.store.toString());
        program_oo.setText(o.programID.toString());
        price_oo.setText(Integer.valueOf(o.price).toString());
        transferTime_oo.setText(o.transferTime.toString());
        VIPId_oo.setText(v.id.toString());
        VIPName_oo.setText(v.name.toString());
        balanceTimes_oo.setText(Integer.valueOf(o.balanceTimes).toString());
        serviceTimes_oo.setText(Integer.valueOf(o.serviceTimes).toString());


    }
}
