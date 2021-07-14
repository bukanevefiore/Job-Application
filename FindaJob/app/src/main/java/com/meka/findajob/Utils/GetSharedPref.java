package com.meka.findajob.Utils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import com.meka.findajob.Activity.SignInActivity;

public class GetSharedPref {

    private SharedPreferences sharedPreferences;
    private Activity activity;

    public GetSharedPref(Activity activity) {

        this.activity = activity;
        sharedPreferences =activity.getApplicationContext().getSharedPreferences("session",0);
    }

    // uygulamaya giriş yapan kullanıcı bilgileri
    public void setSession(String id,String mail,String kadi){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("id",id);
        editor.putString("mail",mail);
        editor.putString("kadi",kadi);
        editor.commit();
    }


    public void sharedSil(){
        SharedPreferences.Editor editor;
        editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
    public void setSession2(String ilanid){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("ilanid",ilanid);
        editor.commit();
    }



    public SharedPreferences getSession(){
        return sharedPreferences;
    }
}
