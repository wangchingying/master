package com.cyw.firebaseauthapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cyw.firebaseauthapp.Data.master;
import com.cyw.firebaseauthapp.OrderData.flag;
import com.cyw.firebaseauthapp.OrderData.order;
import com.cyw.firebaseauthapp.VIPData.VIP;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WaitingMoney_detail extends AppCompatActivity {

    TextView orderId_wmd,masterName_wmd,store_wmd,program_wmd,price_wmd,times_wmd,bankCode_wmd,bankAccount_wmd,deadline_wmd,VIPId_wmd,VIPName_wmd;
    Button payCash_wmd,deleteOrder_wmd;
    String OID,MID;
    order o;
    master m;
    VIP v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_money_detail);
        orderId_wmd=(TextView)findViewById(R.id.orderId_wmd);
        masterName_wmd=(TextView)findViewById(R.id.masterName__wmd);
        store_wmd=(TextView)findViewById(R.id.store__wmd);
        program_wmd=(TextView)findViewById(R.id.program__wmd);;
        price_wmd=(TextView)findViewById(R.id.price__wmd);
        times_wmd=(TextView)findViewById(R.id.times__wmd);
        bankCode_wmd=(TextView)findViewById(R.id.bankCode__wmd);
        bankAccount_wmd=(TextView)findViewById(R.id.bankAccount__wmd);
        deadline_wmd=(TextView)findViewById(R.id.deadline__wmd);
        VIPId_wmd=(TextView)findViewById(R.id.VIPId__wmd);
        VIPName_wmd=(TextView)findViewById(R.id.VIPName_wmd);
        payCash_wmd=(Button)findViewById(R.id.payCash_wmd);
        deleteOrder_wmd=(Button)findViewById(R.id.deleteOrder_wmd);

        OID=getIntent().getStringExtra("OrderID");
        o=MainActivity.dao_o.getOrder(OID);
        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        MID = sp.getString("id", "");
        m=MainActivity.dao_m.getMaster(MID);
        v=MainActivity.dao_v.getVIP(o.customerId);

        orderId_wmd.setText(OID);
        masterName_wmd.setText(m.name.toString());
        store_wmd.setText(m.store.toString());
        program_wmd.setText(o.programID.toString());
        price_wmd.setText(Integer.valueOf(o.price).toString());
        times_wmd.setText(Integer.valueOf(o.balanceTimes).toString());
        bankCode_wmd.setText(m.bankcode.toString());
        bankAccount_wmd.setText(m.accountNumber.toString());
        deadline_wmd.setText(o.deadline.toString());
        VIPId_wmd.setText(v.id.toString());
        VIPName_wmd.setText(v.name.toString());

        payCash_wmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o.flag=flag.OPEN_ORDER;
                o.balanceTimes=Integer.valueOf(times_wmd.getText().toString());
                o.customerfeedback="現金儲值";
                //現金儲值時間(就是現在)
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
                Calendar c = Calendar.getInstance();
                String currentTime = df.format(c.getTime());
                o.transferTime=currentTime;
                o.transferMoney=Integer.valueOf(price_wmd.getText().toString());
                o.serviceTimes=0;

                MainActivity.dao_o.update(o);

                Toast.makeText(WaitingMoney_detail.this, "現金儲值成功,可開始預約", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        deleteOrder_wmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.dao_o.delete(OID);
                finish();
            }
        });

    }
}