package com.cyw.firebaseauthapp.VIPData;

import android.content.Context;

import com.cyw.firebaseauthapp.Data.master;
import com.cyw.firebaseauthapp.Interface.VIPDAOInterface;
import com.cyw.firebaseauthapp.Interface.masterDAOInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/23.
 */

public class VIPCloudDAO implements VIPDAOInterface{

    public Context context;
    public ArrayList<VIP> mylist;
    FirebaseDatabase database;
    DatabaseReference myRef;

    public VIPCloudDAO(Context context) {
        this.context = context;

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("VIPData");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Gson gson = new Gson();
                mylist = gson.fromJson(value, new TypeToken<ArrayList<VIP>>(){}.getType());

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        if (mylist == null)
        {
            mylist = new ArrayList<>();

        }

    }

    public void saveFile(){
        // Write a message to the database
        Gson gson = new Gson();
        String data = gson.toJson(mylist);
        //Log.d("Here", "saveFile"+data);
        myRef.setValue(data);
    }

    @Override
    public boolean add(VIP s) {
        if (mylist == null)
        {
            mylist = new ArrayList<>();
        }

        mylist.add(s);
//        Log.d("Hereadd", "saveFile"+String.valueOf(s.id));

        saveFile();
        return true;
    }

    @Override
    public ArrayList<VIP> getList() {
        return mylist;
    }

    @Override
    public VIP getVIP(String id) {

        for (VIP s : mylist)
        {
            if (s.id.equals(id))
            {
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean update(VIP s) {
        for (VIP t : mylist)
        {
            if (t.id.equals(s.id))
            {
                t.name = s.name;
                t.password = s.password;
                saveFile();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        for (VIP s : mylist)
        {
            if (s.id.equals(id))
            {
                mylist.remove(s);
                saveFile();
                return true;
            }
        }
        return false;
    }

}
