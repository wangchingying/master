package com.cyw.firebaseauthapp.Program;

import android.content.Context;

import com.cyw.firebaseauthapp.Data.master;
import com.cyw.firebaseauthapp.Interface.masterDAOInterface;
import com.cyw.firebaseauthapp.Interface.programDAOInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Student on 2018/1/19.
 */

public class programFileDAO implements programDAOInterface {

    public Context context;
    public ArrayList<program> mylist;

    //新增傳入Context建構式, 取得activity的資訊,才能有getFile可用
    public programFileDAO(Context context)
    {
        this.context=context;
        mylist = new ArrayList<>();
    }
    //存檔
    public void saveFile(){
        File f= new File(context.getFilesDir(),"programData.txt");
        FileWriter fw=null;
        try {
            fw=new FileWriter(f);
            Gson gson=new Gson();
            String data=gson.toJson(mylist);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //讀檔
    public void loadFile(){

        File f=new File(context.getFilesDir(),"programData.txt");
        FileReader fr=null;

        try {
            fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String str=br.readLine();
            Gson gson=new Gson();
            mylist=gson.fromJson(str,new TypeToken<ArrayList<program>>(){}.getType());
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //新增 一個master
    public boolean add(program s)
    {
        mylist.add(s);
        saveFile();
        return true;
    }

    //取得Student陣列
    public ArrayList<program> getList()
    {
        loadFile();
        return mylist;
    }

    //用id取得一個Student資料
    public program getProgram(String masterid,String programid)
    {
        loadFile();
        for (program s : mylist)
        {
            if (s.masterID.equals(masterid)&&s.programID.equals(programid))
            {
                return s;
            }
        }
        return null;
    }


    //用id刪除一個Student資料
    public boolean delete(String masterid,String programid)
    {
        loadFile();
        for (program s : mylist)
        {
            if (s.masterID.equals(masterid)&&s.programID.equals(programid))
            {
                mylist.remove(s);
                saveFile();
                return true;
            }
        }
        return false;
    }


    //傳入一個Student物件並更新
    public boolean update(program s)
    {
        loadFile();
        for (program t : mylist)
        {
            if (t.masterID.equals(s.masterID)&& t.programID.equals(s.programID))
            {
                t.times = s.times;
                t.price = s.price;
                saveFile();
                return true;
            }
        }
        return false;
    }

}
