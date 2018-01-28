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

public class BasicDataMaintainPWD extends AppCompatActivity {

    TextView pwdid;
    EditText oldpwd,newpwd;
    Button pwdsubmit;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_data_maintain_pwd);

        pwdsubmit=(Button) findViewById(R.id.pwdsubmit);
        pwdid=(TextView)findViewById(R.id.pwdid);
        oldpwd =(EditText) findViewById(R.id.oldpwd);
        newpwd =(EditText) findViewById(R.id.newpwd);

        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        ID = sp.getString("id", "");
        pwdid.setText(ID);

        pwdsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.dao_m.getMaster(ID).password.equals(oldpwd.getText().toString())
                        && newpwd.getText().toString()!="")
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(BasicDataMaintainPWD.this);
                    builder.setTitle("修改密碼");
                    builder.setMessage("確定修改?");
                    builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.dao_m.getMaster(ID).password=newpwd.getText().toString();
                            MainActivity.dao_m.saveFile();
                            Toast.makeText(BasicDataMaintainPWD.this, "密碼修改完成", Toast.LENGTH_SHORT).show();
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
                }else{
                    Toast.makeText(BasicDataMaintainPWD.this, "舊密碼錯誤或新密碼空白", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
