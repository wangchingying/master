package com.cyw.firebaseauthapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cyw.firebaseauthapp.OrderData.flag;
import com.cyw.firebaseauthapp.OrderData.order;

import java.sql.Time;
import java.util.ArrayList;

public class TimePeriod_A extends AppCompatActivity {
    ListView lv;
    ArrayList<String> TPAList;
    ArrayList<order> orderList;
    String masterID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_period_a);

        lv=(ListView)findViewById(R.id.listView_TPA);
        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        masterID = sp.getString("id", "");
        orderList=MainActivity.dao_o.getList();

        Myadapter adapter=new Myadapter();

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }

    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
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
            LayoutInflater inflater=LayoutInflater.from(TimePeriod_A.this);
            View v=inflater.inflate(R.layout.myitem,null);
            TextView tv=v.findViewById(R.id.textView);
            //TextView tv1=v.findViewById(R.id.textView32);
            //String OID=closedorderList.get(position).toString();
            //String CID=MainActivity.dao_o.getOrder(OID).customerId;
            //String CName=MainActivity.dao_v.getVIP(CID).name;
            //Log.d("open order","order:"+OID+" VIPid:"+CID+"  VIPname:"+CName);
            //tv.setText("訂單號碼:"+OID);
            //tv1.setText("客戶姓名:"+CName);
            return v;

        }

    }
}
