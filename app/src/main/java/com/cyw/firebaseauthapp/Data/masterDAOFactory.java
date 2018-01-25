package com.cyw.firebaseauthapp.Data;

import android.content.Context;

import com.cyw.firebaseauthapp.Interface.masterDAOInterface;

/**
 * Created by Student on 2018/1/23.
 */

public class masterDAOFactory {
    public static masterDAOInterface getDAOInstance(Context context, DBtype dbType)
    {
        switch (dbType)
        {

            case FILE:
                return new masterDAO(context);

            case CLOUD:
                return new masterCloudDAO(context);
        }
        return null;
    }

}
