package com.cyw.firebaseauthapp;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cyw.firebaseauthapp.Data.master;
import com.cyw.firebaseauthapp.OrderData.order;
import com.cyw.firebaseauthapp.OrderData.orderCloudDAO;
import com.cyw.firebaseauthapp.OrderData.orderFileDAO;
import com.cyw.firebaseauthapp.Program.program;
import com.cyw.firebaseauthapp.VIPData.VIPCloudDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NewOrder extends AppCompatActivity {

    TextView orderID, masterName_order, store_order, price_order, times_order, bankCode_order, bankAccount_order, VIPname_order,deadline;
    EditText VIPID_order,cashValue;
    Spinner spinner_program;
    String masterID;
    int year, month, date, hour, min;
    Button daypickbtn,confirmVIP,payTransfer,payCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        orderID = (TextView) findViewById(R.id.orderID);
        masterName_order = (TextView) findViewById(R.id.masterName_order);
        store_order = (TextView) findViewById(R.id.store_order);
        price_order = (TextView) findViewById(R.id.price_order);
        times_order = (TextView) findViewById(R.id.times_order);
        bankCode_order = (TextView) findViewById(R.id.bankCode_order);
        bankAccount_order = (TextView) findViewById(R.id.bankAccount_order);
        VIPname_order = (TextView) findViewById(R.id.VIPname_order);
        VIPID_order = (EditText) findViewById(R.id.VIPID_order);
        confirmVIP = (Button) findViewById(R.id.confirmVIP);
        daypickbtn = (Button) findViewById(R.id.daypickbtn);
        deadline = (TextView) findViewById(R.id.deadline);
        payTransfer = (Button) findViewById(R.id.payTransfer);
        payCash = (Button) findViewById(R.id.payCash);
        cashValue=(EditText)findViewById(R.id.cashValue);

        spinner_program = (Spinner) findViewById(R.id.spinner_program);

        SharedPreferences sp = getSharedPreferences("basicdata", MODE_PRIVATE);
        masterID = sp.getString("id", "");
        final master masterinfo = MainActivity.dao_m.getMaster(masterID);



        //輸入客戶電話號碼,得到名字
        confirmVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = VIPID_order.getText().toString();
                if(MainActivity.dao_v.getList()==null || MainActivity.dao_v.getVIP(str1)==null){
                    Toast.makeText(NewOrder.this, "號碼輸入錯誤", Toast.LENGTH_SHORT).show();
                }else {
                    String str2 = MainActivity.dao_v.getVIP(str1).name.toString();
                    VIPname_order.setText(str2);
                }
            }
        });


        //用年月日時分當訂單號碼
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        Calendar c = Calendar.getInstance();
        String str = df.format(c.getTime());
        orderID.setText(str);

        masterName_order.setText(masterinfo.name.toString());
        store_order.setText(masterinfo.store.toString());
        bankCode_order.setText(masterinfo.bankcode.toString());
        bankAccount_order.setText(masterinfo.accountNumber.toString());

        //Spinner 顯示療程名稱
        //Log.d("spinner",String.valueOf(MainActivity.dao_p.getProgram("0922896546","1").times));
        final ArrayList<program> plist = MainActivity.dao_p.getList();
        ArrayList<String> programName = new ArrayList<>();
        for (int i = 0; i < plist.size(); i++) {
            if(plist.get(i).masterID.toString().equals(masterID)) {
                programName.add(plist.get(i).programID);
            }
        }
        ArrayAdapter<String> List = new ArrayAdapter<>(NewOrder.this,
                android.R.layout.simple_spinner_dropdown_item,
                programName);
        spinner_program.setAdapter(List);


        //把價格和次數帶出來
        spinner_program.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                price_order.setText(String.valueOf(plist.get(position).price));
                times_order.setText(String.valueOf(plist.get(position).times));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        //選擇匯款期限
        daypickbtn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        //匯款者將資料傳到雲端
        payTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.dao_v.getList()==null || MainActivity.dao_v.getVIP(VIPID_order.getText().toString())==null||deadline.getText().equals("")) {
                    Toast.makeText(NewOrder.this, "請輸入客人帳號或匯款期限", Toast.LENGTH_SHORT).show();
                }else{
                MainActivity.dao_o.add(new order(
                        orderID.getText().toString(),
                        VIPID_order.getText().toString(),
                        masterID,
                        spinner_program.getSelectedItem().toString(),
                        deadline.getText().toString(),
                        "",
                        Integer.valueOf(price_order.getText().toString()),
                        0,
                        0,
                        "匯款"));
                    Toast.makeText(NewOrder.this, "資料以上傳雲端,請通知客戶儲值", Toast.LENGTH_SHORT).show();
                    //Log.d("orderorderorder1",MainActivity.dao_o.getList().toString());

                    finish();
                }
            }
        });

        //付現金就直接儲值變成open order
        payCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cashValue.getText().toString().equals("")||MainActivity.dao_v.getList()==null ||
                        MainActivity.dao_v.getVIP(VIPID_order.getText().toString())==null){
                    Toast.makeText(NewOrder.this, "請輸入客人帳號或儲值現金", Toast.LENGTH_SHORT).show();
                }else{
                    //現金儲值時間(就是現在)
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
                    Calendar c = Calendar.getInstance();
                    String currentTime = df.format(c.getTime());

                    MainActivity.dao_o.add(new order(
                            orderID.getText().toString(),
                            VIPID_order.getText().toString(),
                            masterID,
                            spinner_program.getSelectedItem().toString(),
                            deadline.getText().toString(),
                            currentTime,
                            Integer.valueOf(price_order.getText().toString()),
                            Integer.valueOf(cashValue.getText().toString()),
                            Integer.valueOf(times_order.getText().toString()),
                            "現金儲值"));
                    Toast.makeText(NewOrder.this, "現金儲值成功,可開始預約", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });



    }
    public void showDatePickerDialog() {
                // 設定初始日期
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


                // 跳出日期選擇器
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // 完成選擇，顯示日期
                        deadline.setText(year + "-" + (monthOfYear + 1) + "-"
                                + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();

    }


}

