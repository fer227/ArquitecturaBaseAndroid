package com.app.server.utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    private String urlBase = "http://34.228.27.222:6000/canciones/";

    public String getUrlBase(){
        return urlBase;
    }

    public String getUrlBaseID(int id){
        return urlBase + Integer.toString(id);
    }

    public void enviarToast(String msg, Context context){
        //Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = null;
        toast = Toast.makeText(context, msg, duration);
        toast.show();
    }
}
