package com.cyw.firebaseauthapp.Data;

import android.content.Context;
import android.util.Log;

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
