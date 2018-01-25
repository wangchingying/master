package com.cyw.firebaseauthapp.Interface;

import com.cyw.firebaseauthapp.Data.master;
import com.cyw.firebaseauthapp.OrderData.order;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/23.
 */

public interface orderDAOInterface {
    //增加一個物件
    public boolean add(order s);
    //抓取物件陣列
    public ArrayList<order> getList();
    //抓取一個物件
    public order getOrder(String id);
    //更新一個物件
    public boolean update(order s);
    //刪除一個物件
    public boolean delete(String id);
}