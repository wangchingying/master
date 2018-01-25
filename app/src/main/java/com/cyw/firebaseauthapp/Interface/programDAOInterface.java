package com.cyw.firebaseauthapp.Interface;

import com.cyw.firebaseauthapp.OrderData.order;
import com.cyw.firebaseauthapp.Program.program;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/23.
 */

public interface programDAOInterface {
    //增加一個物件
    public boolean add(program s);
    //抓取物件陣列
    public ArrayList<program> getList();
    //抓取一個物件
    public program getProgram(String id);
    //更新一個物件
    public boolean update(program s);
    //刪除一個物件
    public boolean delete(String id);
}