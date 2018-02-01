package com.cyw.firebaseauthapp;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class StoreMap extends AppCompatActivity {
    WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_map);
        wv1=(WebView)findViewById(R.id.store_webView);
        Log.d("fromto","test");
        wv1.setWebViewClient(new WebViewClient());
        wv1.getSettings().setJavaScriptEnabled(true);
        Log.d("fromto","test1");
        String[] fromto= getIntent().getStringArrayExtra("fromto");
        Log.d("fromto","https://www.google.com.tw/maps/dir/"+fromto[0]+"/"+fromto[1]);
        wv1.loadUrl("https://www.google.com.tw/");//+fromto[0]+"/"+fromto[1]);
    }
}
