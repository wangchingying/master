package com.cyw.firebaseauthapp.Interface;

import com.cyw.firebaseauthapp.Data.master;
import com.cyw.firebaseauthapp.VIPData.VIP;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/23.
 */

public interface VIPDAOInterface {
    //增加一個物件
    public boolean add(VIP s);
    //抓取物件陣列
    public ArrayList<VIP> getList();
    //抓取一個物件
    public VIP getVIP(String id);
    //更新一個物件
    public boolean update(VIP s);
    //刪除一個物件
    public boolean delete(String id);
}