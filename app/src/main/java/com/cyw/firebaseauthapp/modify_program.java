package com.cyw.firebaseauthapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cyw.firebaseauthapp.Data.master;
import com.cyw.firebaseauthapp.Program.program;

import org.w3c.dom.Text;

public class modify_program extends AppCompatActivity {

    TextView masterName_mp,programId_mp;
    EditText price_mp,times_mp;
    Button modify_program_btn,delete_program_btn;
    String MID,PID;
    String [] str;
    program p;
    master m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_program);
        masterName_mp=(TextView)findViewById(R.id.masterName_mp);
        programId_mp=(TextView)findViewById(R.id.programId_mp);
        price_mp=(EditText)findViewById(R.id.price_mp);
        times_mp=(EditText)findViewById(R.id.times_mp);
        modify_program_btn=(Button)findViewById(R.id.modify_program_btn);
        delete_program_btn=(Button)findViewById(R.id.delete_program_btn);
        Intent it=getIntent();
        String[] str=it.getStringArrayExtra("MIDPID");
        MID=str[0];
        PID=str[1];
        Log.d("MIDPID","MID:"+MID+", PID:"+PID);
        p=MainActivity.dao_p.getProgram(MID,PID);
        m=MainActivity.dao_m.getMaster(MID);
        masterName_mp.setText(m.name.toString());
        programId_mp.setText(PID);
        price_mp.setText(Integer.valueOf(p.price).toString());
        times_mp.setText(Integer.valueOf(p.times).toString());

        modify_program_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.price=Integer.valueOf(price_mp.getText().toString());
                p.times=Integer.valueOf(times_mp.getText().toString());
                MainActivity.dao_p.update(p);
                Toast.makeText(modify_program.this, "此療程已更新", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        delete_program_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    AlertDialog.Builder builder =new AlertDialog.Builder(modify_program.this);
                    builder.setTitle("刪除療程");
                    builder.setMessage("確認刪除此療程?");
                    builder.setPositiveButton("刪除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.dao_p.delete(MID,PID);
                            Toast.makeText(modify_program.this, "此療程已刪除", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
            }
        });
    }

}
