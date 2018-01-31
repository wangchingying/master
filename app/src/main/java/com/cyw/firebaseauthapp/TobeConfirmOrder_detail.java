package com.cyw.firebaseauthapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cyw.firebaseauthapp.Data.master;
import com.cyw.firebaseauthapp.OrderData.order;
import com.cyw.firebaseauthapp.VIPData.VIP;

public class TobeConfirmOrder_detail extends AppCompatActivity {

    TextView orderID_tbcd,masterName_tbcd,store_tbcd,programId_tbcd,price_tbcd,balanceTimes_tbcd,bankCode_tbcd,bankAccount_tbcd,deadline_tbcd,transferTime_tbcd,VIPId_tbcd,VIPName_tbcd,customerFeedback_tbcd;
    Button confirmTransfer_btn;
    String MID,OID;
    order o;
    VIP v;
    master m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tobe_confirm_order_detail);
        orderID_tbcd=(TextView)findViewById(R.id.orderID_tbcd);
        masterName_tbcd=(TextView)findViewById(R.id.masterName_tbcd);
        store_tbcd=(TextView)findViewById(R.id.store_tbcd);
        programId_tbcd=(TextView)findViewById(R.id.programId_tbcd);
        price_tbcd=(TextView)findViewById(R.id.price_tbcd);
        balanceTimes_tbcd=(TextView)findViewById(R.id.balanceTimes_tbcd);
        bankCode_tbcd=(TextView)findViewById(R.id.bankCode_tbcd);
        bankAccount_tbcd=(TextView)findViewById(R.id.bankAccount_tbcd);
        deadline_tbcd=(TextView)findViewById(R.id.deadline_tbcd);
        transferTime_tbcd=(TextView)findViewById(R.id.transferTime_tbcd);
        VIPId_tbcd=(TextView)findViewById(R.id.VIPId_tbcd);
        VIPName_tbcd=(TextView)findViewById(R.id.VIPName_tbcd);
        customerFeedback_tbcd=(TextView)findViewById(R.id.customerFeedback_tbcd);
        confirmTransfer_btn=(Button)findViewById(R.id.confirmTransfer_btn);

        OID = getIntent().getStringExtra("OrderID");
        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        MID = sp.getString("id", "");
        m=MainActivity.dao_m.getMaster(MID);
        o=MainActivity.dao_o.getOrder(OID);
        v=MainActivity.dao_v.getVIP(o.customerId);

        orderID_tbcd.setText(OID);
        masterName_tbcd.setText(m.name.toString());
        store_tbcd.setText(m.store.toString());
        programId_tbcd.setText(o.programID.toString());
        price_tbcd.setText(Integer.valueOf(o.price).toString());
        balanceTimes_tbcd.setText(Integer.valueOf(o.balanceTimes).toString());
        bankCode_tbcd.setText(m.bankcode.toString());
        bankAccount_tbcd.setText(m.accountNumber.toString());
        deadline_tbcd.setText(o.deadline.toString());
        transferTime_tbcd.setText(o.transferTime.toString());
        VIPId_tbcd.setText(o.customerId.toString());
        VIPName_tbcd.setText(v.name.toString());
        customerFeedback_tbcd.setText(o.customerfeedback.toString());

        confirmTransfer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
