package com.meka.findajob.Utils;

import android.app.Activity;
import android.content.SharedPreferences;

public class GetSharedPref {

    private SharedPreferences sharedPreferences;
    private Activity activity;

    public GetSharedPref(Activity activity) {

        this.activity = activity;
        sharedPreferences =activity.getApplicationContext().getSharedPreferences("session",0);
    }

    public void setSession(String id,String mail,String kadi){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("id",id);
        editor.putString("mail",mail);
        editor.putString("kadi",kadi);
        editor.commit();
    }


    public SharedPreferences getSession(){
        return sharedPreferences;
    }
}
