package com.cyw.firebaseauthapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cyw.firebaseauthapp.Data.master;
import com.cyw.firebaseauthapp.Program.program;
import com.cyw.firebaseauthapp.Program.programCloudDAO;
import com.cyw.firebaseauthapp.Program.programFileDAO;

import java.util.ArrayList;

public class BasicDataMaintainProgram extends AppCompatActivity {

    ListView lv1;
    String ID;
//    public static programFileDAO dao;
    Myadapter adapter;
    //ArrayList<program> programArrayList;

    //boolean fastback=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_data_maintain_program);
        lv1 = (ListView) findViewById(R.id.listViewp);

        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        ID = sp.getString("id", "");

//        dao = new programFileDAO(BasicDataMaintainProgram.this);

        adapter = new Myadapter();
        lv1.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
//        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });
    }

    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return MainActivity.dao_p.mylist.size();
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
            LayoutInflater inflater = LayoutInflater.from(BasicDataMaintainProgram.this);
            View v = inflater.inflate(R.layout.myitem_program, null);
            TextView tv1 = v.findViewById(R.id.textView9);
            TextView tv2 = v.findViewById(R.id.textView10);
            TextView tv3 = v.findViewById(R.id.textView11);
            TextView tv4 = v.findViewById(R.id.textView12);
            TextView tv5 = v.findViewById(R.id.textView13);
            TextView tv6 = v.findViewById(R.id.textView14);
            TextView tv7 = v.findViewById(R.id.textView15);
            TextView tv8 = v.findViewById(R.id.textView16);
            //Toast.makeText(MasterBasicData.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            //Log.d("here_Myadapter", String.valueOf(dao.getList().size()));

            tv5.setText(MainActivity.dao_m.getMaster(ID).name.toString());
            tv6.setText(MainActivity.dao_p.getList().get(position).programID.toString());
            tv7.setText(Integer.valueOf(MainActivity.dao_p.getList().get(position).price).toString());
            tv8.setText(Integer.valueOf(MainActivity.dao_p.getList().get(position).times).toString());

            return v;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            Intent it = new Intent(BasicDataMaintainProgram.this, AddProgram.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
//        refreshData();
        //Log.d("here_onResume", String.valueOf(dao.getList().size()));
    }

    //此副程式讓app在一開啟的時候能把資料先讀出來
//    public void refreshData() {
//        programArrayList.clear();
//        for (program s : dao.getList()) {
//            programArrayList.add(new program(s.programID, s.masterID, s.price, s.times));
//        }
//        adapter.notifyDataSetChanged();
//    }
}