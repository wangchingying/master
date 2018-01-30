package com.cyw.firebaseauthapp.OrderData;

import android.content.Context;
import android.util.Log;
import com.cyw.firebaseauthapp.Data.master;
import com.cyw.firebaseauthapp.Interface.orderDAOInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by USER on 2018/1/25.
 */

public class orderCloudDAO implements orderDAOInterface {

    public Context context;
    public ArrayList<order> mylist;
    FirebaseDatabase database;
    DatabaseReference myRef;

    public orderCloudDAO(Context context) {
        this.context = context;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("orderData");
        if (mylist == null)
        {
            mylist = new ArrayList<>();
        }

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Gson gson = new Gson();

                mylist = gson.fromJson(value, new TypeToken<ArrayList<order>>(){}.getType());
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }

    public void saveFile(){
        // Write a message to the database
        Gson gson = new Gson();
        String data = gson.toJson(mylist);
        //Log.d("Here", "saveFile"+data);
        myRef.setValue(data);
    }

    @Override
    public boolean add(order s) {
        if (mylist == null)
        {
            mylist = new ArrayList<>();
        }

        mylist.add(s);
        //Log.d("addorder", "saveFile"+String.valueOf(mylist.size())+"orderID:"+String.valueOf(s.orderId));

        saveFile();
        return true;
    }

    @Override
    public ArrayList<order> getList() {
        return mylist;
    }

    @Override
    public order getOrder(String id) {

        for (order s : mylist)
        {
            if (s.orderId.equals(id))
            {
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean update(order s) {
        for (order t : mylist)
        {
            if (t.orderId.equals(s.orderId))
            {
                t.transferTime = s.transferTime;
                t.transferMoney = s.transferMoney;
                t.balanceTimes=s.balanceTimes;
                t.serviceTimes=s.serviceTimes;
                t.customerfeedback=s.customerfeedback;
                t.flag=s.flag;

                saveFile();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        for (order s : mylist)
        {
            if (s.orderId.equals(id))
            {
                mylist.remove(s);
                saveFile();
                return true;
            }
        }
        return false;
    }
}

