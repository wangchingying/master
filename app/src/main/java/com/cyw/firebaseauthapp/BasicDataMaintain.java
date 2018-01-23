package com.cyw.firebaseauthapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BasicDataMaintain extends AppCompatActivity {

    TextView mdid;
    EditText mdname,mdstore,mdbankcode,mdbankaccount;
    Button mdsubmit;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_data_maintain);

        mdsubmit=(Button) findViewById(R.id.pwdsubmit);
        mdid=(TextView)findViewById(R.id.pwdid);
        mdname =(EditText) findViewById(R.id.mdName);
        mdstore =(EditText) findViewById(R.id.mdstore);
        mdbankcode =(EditText) findViewById(R.id.mdbankcode);
        mdbankaccount =(EditText) findViewById(R.id.mdbankaccount);

        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        ID = sp.getString("id", "");
        mdid.setText(ID);
        mdname.setText(MainActivity.dao.getMaster(ID).name.toString());
        mdstore.setText(MainActivity.dao.getMaster(ID).store.toString());
        mdbankcode.setText(MainActivity.dao.getMaster(ID).bankcode.toString());
        mdbankaccount.setText(MainActivity.dao.getMaster(ID).accountNumber.toString());

        mdsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(BasicDataMaintain.this);
                builder.setTitle("修改基本資料");
                builder.setMessage("確定修改?");
                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.dao.getMaster(ID).name=mdname.getText().toString();
                        MainActivity.dao.getMaster(ID).store=mdstore.getText().toString();
                        MainActivity.dao.getMaster(ID).bankcode=mdbankcode.getText().toString();
                        MainActivity.dao.getMaster(ID).accountNumber=mdbankaccount.getText().toString();
                        MainActivity.dao.saveFile();
                        Toast.makeText(BasicDataMaintain.this, "更新完成", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                });
                builder.show();
            }
        });
    }
}
