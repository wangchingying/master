package com.cyw.firebaseauthapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cyw.firebaseauthapp.Data.master;

public class RegisterActivity extends AppCompatActivity {

    EditText id,name,password,store,bankCode,bankAccount;
    Button registerSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        id=(EditText)findViewById(R.id.id);
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        store=(EditText)findViewById(R.id.store);
        bankCode=(EditText)findViewById(R.id.bankCode);
        bankAccount=(EditText)findViewById(R.id.bankAccount);

        registerSubmit=(Button)findViewById(R.id.registerSubmit);
        registerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.getText().toString().equals("")||password.getText().toString().equals("")||name.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "手機號碼與密碼與姓名不可空白", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    //彈出註冊成功資料, 請重新登入, 再傳送資料
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("註冊資料填寫完成");
                    builder.setMessage("按確認送出, 並於主畫面重新登入");
                    builder.setPositiveButton("確認送出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity.dao.add(new master(id.getText().toString(),  name.getText().toString(), password.getText().toString(),store.getText().toString(), bankCode.getText().toString(), bankAccount.getText().toString()));
                            Toast.makeText(RegisterActivity.this, "請重新登入", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    });
                    builder.setNegativeButton("再次確認", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //do nothing
                        }
                    });
                    builder.show();
                }
            }

        });

    }
    public boolean addMaster(){

        return true;
    }

}
