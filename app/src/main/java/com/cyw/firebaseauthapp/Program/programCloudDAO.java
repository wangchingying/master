package com.cyw.firebaseauthapp.Program;

import com.cyw.firebaseauthapp.BasicDataMaintainProgram;
import com.cyw.firebaseauthapp.Interface.programDAOInterface;
import android.content.Context;
import android.util.Log;

import com.cyw.firebaseauthapp.MainActivity;
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
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("programData");
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
                mylist = gson.fromJson(value, new TypeToken<ArrayList<program>>(){}.getType());
                Log.d("here_onDataChange",String.valueOf(mylist.size()));
//                ((BasicDataMaintainProgram) context).refreshData();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        Log.d("here_Constructor",String.valueOf(mylist.size()));
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
    public program getProgram(String masterid,String programid) {

        for (program s : mylist)
        {
            if (s.masterID.equals(masterid)&&s.programID.equals(programid))
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
            if (t.masterID.equals(s.masterID)&&t.programID.equals(s.programID))
            {
                t.price = s.price;
                t.times=s.times;
               saveFile();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String masterid,String programid) {
        for (program s : mylist)
        {
            if (s.masterID.equals(s.masterID)&&s.programID.equals(s.programID))
            {
                mylist.remove(s);
                saveFile();
                return true;
            }
        }
        return false;
    }
}
