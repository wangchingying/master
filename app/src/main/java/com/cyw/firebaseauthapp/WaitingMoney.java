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
import com.cyw.firebaseauthapp.VIPData.VIP;

import java.util.ArrayList;

public class WaitingMoney extends AppCompatActivity {
    ListView lv;
    ArrayList<String> wMoneyList;
    ArrayList<order> orderList;
    String masterID;
    Myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_money);
        lv=(ListView)findViewById(R.id.listView_waitingmoney);

        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        masterID = sp.getString("id", "");
        orderList=MainActivity.dao_o.getList();
        wMoneyList=new ArrayList<>();




    }
    @Override
    protected void onResume() {
        super.onResume();
        wMoneyList.clear();
        for(int i=0;i<orderList.size();i++)
        {
            if(orderList.get(i).masterId.toString().equals(masterID)
                    &&(orderList.get(i).flag.equals(flag.WAIT_TRANSFER)))
            {
                //Log.d("order","抓的"+orderList.get(i).masterId.toString()+"原本:"+masterID);
                wMoneyList.add(orderList.get(i).orderId);
            }

        }


        adapter=new Myadapter();
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(WaitingMoney.this,WaitingMoney_detail.class);
                String OID=wMoneyList.get(i).toString();
                it.putExtra("OrderID",OID);
                startActivity(it);
            }
        });
    }

    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return wMoneyList.size();
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
            View v=inflater.inflate(R.layout.myitem,null);
            TextView tv=v.findViewById(R.id.textView);
            //TextView tv1=v.findViewById(R.id.VIPname);
            String OID=wMoneyList.get(position).toString();
            //String CID=MainActivity.dao_o.getOrder(OID).customerId;
            //String CName=MainActivity.dao_v.getVIP(CID).name;
            //Log.d("waiting Transfer","order:"+OID+" VIPid:"+CID+"  VIPname:"+CName);
            tv.setText("訂單號碼:"+OID);
            //tv1.setText("客戶姓名:"+CName);
            return v;

        }
    }




}
