package com.cyw.firebaseauthapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        masterName=(TextView)findViewById(R.id.masterName_order);
        programID=(EditText)findViewById(R.id.programID);
        programPrice=(EditText)findViewById(R.id.programPrice);
        programTimes=(EditText)findViewById(R.id.programTimes);
        addbtn=(Button)findViewById(R.id.addbtn);

        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        ID = sp.getString("id", "");

        masterName.setText(MainActivity.dao_m.getMaster(ID).name.toString());

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PID=programID.getText().toString();
                String PP=programPrice.getText().toString();
                String PT=programTimes.getText().toString();
                program p=MainActivity.dao_p.getProgram(ID,PID);

                if(PID.equals("")||PP.equals("")||PT.equals("")) {
                    Toast.makeText(AddProgram.this, "請輸入療程資料", Toast.LENGTH_SHORT).show();
                }else if (p==null ) {
                    MainActivity.dao_p.add(new program(
                    programID.getText().toString(),
                    ID,
                    Integer.valueOf(PP),
                    Integer.valueOf(PT))
                    );
                    finish();
                }else{
                    Toast.makeText(AddProgram.this, "療程:"+PID+"已經定義過了,請用修改!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
