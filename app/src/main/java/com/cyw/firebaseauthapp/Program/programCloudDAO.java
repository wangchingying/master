package com.cyw.firebaseauthapp.Program;

import com.cyw.firebaseauthapp.Interface.programDAOInterface;
import android.content.Context;

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

public class programCloudDAO implements programDAOInterface {

    public Context context;
    public ArrayList<program> mylist;
    FirebaseDatabase database;
    DatabaseReference myRef;

    public programCloudDAO(Context context) {
        this.context = context;
 //       mylist = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("programData");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Gson gson = new Gson();
                mylist = gson.fromJson(value, new TypeToken<ArrayList<program>>(){}.getType());
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
    public boolean add(program s) {
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
    public ArrayList<program> getList() {
        return mylist;
    }

    @Override
    public program getProgram(String id) {

        for (program s : mylist)
        {
            if (s.programID.equals(id))
            {
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean update(program s) {
        for (program t : mylist)
        {
            if (t.programID.equals(s.programID))
            {
//                t.masterID = s.masterID;
                t.price = s.price;
                t.times=s.times;
               saveFile();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        for (program s : mylist)
        {
            if (s.programID.equals(id))
            {
                mylist.remove(s);
                saveFile();
                return true;
            }
        }
        return false;
    }
}
