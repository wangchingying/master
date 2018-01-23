package com.cyw.firebaseauthapp.Data;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/23.
 */

public interface masterDAOInterface {
    //增加一個物件
    public boolean add(master s);
    //抓取物件陣列
    public ArrayList<master> getList();
    //抓取一個物件
    public master getMaster(String id);
    //更新一個物件
    public boolean update(master s);
    //刪除一個物件
    public boolean delete(String id);
}