package com.cyw.firebaseauthapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cyw.firebaseauthapp.OrderData.order;

import java.util.ArrayList;

public class WaitingMoney extends AppCompatActivity {
    ListView lv;
    ArrayList<String> wMoneyList;
    ArrayList<order> orderList;
    String masterID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_money);
        lv=(ListView)findViewById(R.id.listView);

        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        masterID = sp.getString("id", "");
        orderList=MainActivity.dao_o.getList();
        wMoneyList=new ArrayList<>();
        for(int i=0;i<orderList.size();i++)
        {
            if(orderList.get(i).masterId.toString().equals(masterID)
                    &&(orderList.get(i).transferMoney==0))
            {
                wMoneyList.add(orderList.get(i).orderId);
            }

        }


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
            return orderList.size();
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
            LayoutInflater inflater=LayoutInflater.from(WaitingMoney.this);
            View v=inflater.inflate(R.layout.myitem_order,null);
            TextView tv=v.findViewById(R.id.OID);
            TextView tv1=v.findViewById(R.id.VIPname);
            String CID=orderList.get(position).customerId.toString();
            String CName=MainActivity.dao_v.getVIP(CID).name.toString();
            tv.setText("訂單號碼:"+orderList.get(position).orderId);
            tv1.setText("客戶姓名:"+CName);
            return v;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //if(fastback){finish();}//使用者按了clickedit時fastback就變true
    }


}
