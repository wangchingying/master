package com.cyw.firebaseauthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    ListView lv;
    String[] str= {"新訂單","待儲值訂單","未結訂單","已結訂單"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        lv=(ListView)findViewById(R.id.listView);
        OrderActivity.Myadaptor adapter=new OrderActivity.Myadaptor();
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        Intent it1=new Intent(OrderActivity.this,NewOrder.class);
                        startActivity(it1);
                        break;
                    case 1:
                        Intent it2=new Intent(OrderActivity.this,WaitingMoney.class);
                        startActivity(it2);
                        break;
                    case 2:
                        Intent it3=new Intent(OrderActivity.this,OpenOrder.class);
                        startActivity(it3);
                        break;
                    case 3:
                        Intent it4=new Intent(OrderActivity.this,ClosedOrder.class);
                        startActivity(it4);
                        break;
                }


            }
        });
    }

    class Myadaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return str.length;
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
            LayoutInflater inflater=LayoutInflater.from(OrderActivity.this);
            View v=inflater.inflate(R.layout.myitem,null);
            TextView tv=v.findViewById(R.id.textView);
            //Toast.makeText(MasterBasicData.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            tv.setText(str[position]);
            return v;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //if(fastback){finish();}//使用者按了clickedit時fastback就變true
    }

}

