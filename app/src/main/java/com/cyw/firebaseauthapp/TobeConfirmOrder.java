package com.cyw.firebaseauthapp;

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

import com.cyw.firebaseauthapp.OrderData.order;

import java.util.ArrayList;

public class TobeConfirmOrder extends AppCompatActivity {
    ListView lv;
    ArrayList<String> tbcList;
    ArrayList<order> orderList;
    String masterID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tobe_confirm_order);
        lv=(ListView)findViewById(R.id.listView_tbc);
        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        masterID = sp.getString("id", "");
        orderList=MainActivity.dao_o.getList();
        tbcList=new ArrayList<>();

        for(int i=0;i<orderList.size();i++)
        {
            if(orderList.get(i).masterId.toString().equals(masterID)
                    &&(orderList.get(i).transferMoney>0)&&(orderList.get(i).balanceTimes==0)&&(orderList.get(i).serviceTimes==0))
            {
                Log.d("order","抓的"+orderList.get(i).masterId.toString()+"原本:"+masterID);
                tbcList.add(orderList.get(i).orderId);
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
            return tbcList.size();
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
            LayoutInflater inflater=LayoutInflater.from(TobeConfirmOrder.this);
            View v=inflater.inflate(R.layout.myitem_tbc_order,null);
            TextView tv=v.findViewById(R.id.textView22);
            TextView tv1=v.findViewById(R.id.textView23);
            String OID=tbcList.get(position).toString();
            String CID=MainActivity.dao_o.getOrder(OID).customerId;
            String CName=MainActivity.dao_v.getVIP(CID).name;
            Log.d("tbc order","order:"+OID+" VIPid:"+CID+"  VIPname:"+CName);
            tv.setText("訂單號碼:"+OID);
            //tv1.setText("客戶姓名:"+CName);
            return v;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //wMoneyList.clear();
    }


}
