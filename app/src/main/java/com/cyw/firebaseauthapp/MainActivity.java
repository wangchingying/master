package com.cyw.firebaseauthapp;

import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cyw.firebaseauthapp.Data.DBtype;
import com.cyw.firebaseauthapp.Data.masterCloudDAO;
import com.cyw.firebaseauthapp.Data.masterDAOFactory;
import com.cyw.firebaseauthapp.Data.masterDAOInterface;


public class MainActivity extends AppCompatActivity {

    DBtype dbType;
    Button login_btn,register_btn;
    EditText id,password;
    public static masterCloudDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dbType = DBtype.CLOUD; // 1:檔案 2:Firebase CLOUD
        //dao = masterDAOFactory.getDAOInstance(this, dbType);
        dao = new masterCloudDAO(MainActivity.this);
        login_btn = (Button) findViewById(R.id.login_btn);
        register_btn = (Button) findViewById(R.id.register_btn);
        id = (EditText) findViewById(R.id.passwordold);
        password = (EditText) findViewById(R.id.password);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(id.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "請輸入手機號碼,不能空白喔~", Toast.LENGTH_SHORT).show();
                }else if(password.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "請輸入密碼,不能空白喔~", Toast.LENGTH_SHORT).show();
                }else if(loginSuccess()){
                Intent it = new Intent(MainActivity.this, MasterActivity.class);
                startActivity(it);
                }else {
                Toast.makeText(MainActivity.this, "帳號密碼錯誤!!", Toast.LENGTH_SHORT).show();
                }
            }

            //比對電話密碼
            public boolean loginSuccess() {
                Boolean result = false;
                String ID = id.getText().toString();
                String PWD = password.getText().toString();
                //Toast.makeText(MainActivity.this,dao.getMaster(ID).password, Toast.LENGTH_SHORT).show();
                if (dao.getMaster(ID) == null) {
                    Toast.makeText(MainActivity.this, "無此電話號碼,請修改或重新註冊!!", Toast.LENGTH_SHORT).show();
                } else if (dao.getMaster(ID).password.equals(PWD)) {

                    SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("id",ID);
                    editor.commit();

                    result = true;
                } else {
                    Toast.makeText(MainActivity.this, "密碼錯誤", Toast.LENGTH_SHORT).show();
                }
                return result;
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(it);
            }
        });

    }
}





















