package com.cyw.firebaseauthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class BasicDataMaintain extends AppCompatActivity {

    TextView id;
    EditText oldpwd,newpwd,name,store,bankcode,bankaccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_data_maintain);

        id=(TextView)findViewById(R.id.id);
        //id.setText(MainActivity.dao.getMaster(MasterID));
    }
}
