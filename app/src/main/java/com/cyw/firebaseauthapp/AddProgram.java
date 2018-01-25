package com.cyw.firebaseauthapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cyw.firebaseauthapp.Program.program;

public class AddProgram extends AppCompatActivity {

    String ID;
    TextView masterName;
    EditText programID;
    EditText programPrice;
    EditText programTimes;
    Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);

        masterName=(TextView)findViewById(R.id.masterName);
        programID=(EditText)findViewById(R.id.programID);
        programPrice=(EditText)findViewById(R.id.programPrice);
        programTimes=(EditText)findViewById(R.id.programTimes);
        addbtn=(Button)findViewById(R.id.addbtn);

        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        ID = sp.getString("id", "");

        masterName.setText(MainActivity.dao.getMaster(ID).name.toString());

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasicDataMaintainProgram.dao.add(new program(
                        programID.getText().toString(),
                        ID,
                        Integer.valueOf(programPrice.getText().toString()),
                        Integer.valueOf(programTimes.getText().toString())
                        )
                );
                finish();
            }
        });


    }
}
