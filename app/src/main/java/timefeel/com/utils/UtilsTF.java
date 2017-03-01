package timefeel.com.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import java.io.IOException;
import java.io.InterruptedIOException;

import timefeel.com.CustomApplication;

/**
 * Created by test on 27/02/2017.
 */

public class  UtilsTF {

    private UtilsTF(){

    }
    public static Boolean isNetwrkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) CustomApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo  activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static Boolean isOnline(){
        Runtime runtime = Runtime.getRuntime();
        try{
            java.lang.Process ipprocess = runtime.exec("/system/bin/ping -c 8.8.8.8");
        }
        catch (InterruptedIOException e){e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}
        return false;
    }
}
