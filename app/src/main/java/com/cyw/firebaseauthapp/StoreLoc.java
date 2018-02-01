package com.cyw.firebaseauthapp;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cyw.firebaseauthapp.Store.Store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class StoreLoc extends AppCompatActivity {
    LocationManager lm;
    private static final int REQUEST_LOCATION = 123;
    ListView lv1;
    ArrayList<Store> stores;
    Myadapter adapter;
    Address addr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_loc);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        lv1 = (ListView) findViewById(R.id.store_listView);
        adapter = new Myadapter();
        stores = new ArrayList<>();
        stores.add(new Store("林口店", "新北市林口區文化二路一段563-3號1樓", "02 2602 5511", R.drawable.store001));
        stores.add(new Store("中和店", "新北市中和區景新街340號", "02 2602 5511", R.drawable.store002));
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (ActivityCompat.checkSelfPermission(StoreLoc.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(StoreLoc.this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                     ActivityCompat.requestPermissions(
//                            StoreLoc.this,
//                            new String[]{ACCESS_FINE_LOCATION,
//                                    ACCESS_COARSE_LOCATION},
//                            REQUEST_LOCATION
//                    );
//                    Log.d("here", "P return");
//
//                    return;
//                } else {
//                    //重要:上面的if是下面這行燈泡紅字時選擇第一個add permission check出現的,然後會自己出現一個 MyListener的class的檔案
//                    Log.d("here", "P else startLoc ");
//                    startLoc();
                    Intent it=new Intent(StoreLoc.this,StoreMap.class);

                    String[] fromto={addr.toString(),stores.get(i).address.toString()};
                    it.putExtra("fromto",fromto);
                    startActivity(it);
//                }



            }

        });
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_LOCATION) {
//            if (grantResults.length > 0
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                //取得權限，進行檔案存取
//                Log.d("here", "onRequestP");
//                startLoc();
//            } else {
//
//            }
//        }
//    }
//    //定位的method
//    private void startLoc() {
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new MyListener());
//    }
//    //順序1.5, 執行LocationListener介面, 當位置改變時,會執行下列複寫方法
//    class MyListener implements LocationListener {
//
//        @Override
//        public void onLocationChanged(Location location) {
//            Log.d("LOC", "Change!!"+ location.getLatitude() + "," + location.getLongitude());
//
//            //台北101的位置
//            Location loc101 = new Location("LOC");
//            loc101.setLatitude(25.0336);
//            loc101.setLongitude(121.5646);
//
//            //台北101與目前位置的距離, 超過100公里就不準, 因為地球是圓的,這個算法是直線距離
//            float dist = location.distanceTo(loc101);
//            Log.d("LOC", "Dist:" + dist);
//
//            //這段把地址抓回來, 有IOException的原因是因為只有android手機可以去抓,不然所有導航機都去抓google也太笨了
//            Geocoder geocoder = new Geocoder(StoreLoc.this);
//            try {
//                List<Address> mylist = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                addr = mylist.get(0);
//                Log.d("LOC", addr.getAddressLine(0));
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        @Override
//        public void onStatusChanged(String s, int i, Bundle bundle) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String s) {
//
//        }
//
//        @Override
//        public void onProviderDisabled(String s) {
//
//        }
//    }

    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return stores.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(StoreLoc.this);
            View v = inflater.inflate(R.layout.myitem_store, null);
            TextView tv = v.findViewById(R.id.store_name);
            TextView tv1 = v.findViewById(R.id.store_addr);
            TextView tv2 = v.findViewById(R.id.store_tel);
            ImageView iv = v.findViewById(R.id.store_pic);
            tv.setText(stores.get(position).storeName);
            tv1.setText(stores.get(position).address);
            tv2.setText(stores.get(position).tel);
            iv.setImageResource(stores.get(position).pic);
            return v;
        }
    }

}






