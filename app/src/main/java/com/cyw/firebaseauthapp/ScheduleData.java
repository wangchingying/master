package com.cyw.firebaseauthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ScheduleData extends AppCompatActivity {
WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_data2);

        wv1=(WebView)findViewById(R.id.webView);
        wv1.setWebViewClient(new WebViewClient());
        //wv1.setWebChromeClient(new WebChromeClient());
        //開啟javascript功能才能看更多內容
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.loadUrl("https://calendar.google.com/calendar?cid=d2FuZ2NoaW5neWluZ0BnbWFpbC5jb20");


    }
}
