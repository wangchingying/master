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

import com.cyw.firebaseauthapp.Program.programCloudDAO;

public class BasicDataMaintainProgram extends AppCompatActivity {

    ListView lv1;
    String ID;
    public static programCloudDAO dao;

    //boolean fastback=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_data_maintain_program);
        dao=new programCloudDAO(BasicDataMaintainProgram.this);
        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        ID = sp.getString("id", "");

        lv1=(ListView)findViewById(R.id.listViewp);
        Myadaptor adapter=new Myadaptor();
        lv1.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
        Log.d("here1",BasicDataMaintainProgram.dao.getList().toString());
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    class Myadaptor extends BaseAdapter     {

        @Override
        public int getCount() {
            return BasicDataMaintainProgram.dao.mylist.size();
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
            LayoutInflater inflater=LayoutInflater.from(BasicDataMaintainProgram.this);
            View v=inflater.inflate(R.layout.myitem_program,null);
            TextView tv1=v.findViewById(R.id.textView9);
            TextView tv2=v.findViewById(R.id.textView10);
            TextView tv3=v.findViewById(R.id.textView11);
            TextView tv4=v.findViewById(R.id.textView12);
            TextView tv5=v.findViewById(R.id.textView13);
            TextView tv6=v.findViewById(R.id.textView14);
            TextView tv7=v.findViewById(R.id.textView15);
            TextView tv8=v.findViewById(R.id.textView16);
            //Toast.makeText(MasterBasicData.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            tv5.setText(MainActivity.dao.getMaster(ID).name.toString());
            tv6.setText(BasicDataMaintainProgram.dao.getList().get(position).programID.toString());
            tv7.setText(Integer.valueOf(BasicDataMaintainProgram.dao.getList().get(position).price).toString());
            tv8.setText(Integer.valueOf(BasicDataMaintainProgram.dao.getList().get(position).times).toString());
            return v;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //if(fastback){finish();}//使用者按了clickedit時fastback就變true
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add)
        {
            Intent it = new Intent(BasicDataMaintainProgram.this, AddProgram.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
}
