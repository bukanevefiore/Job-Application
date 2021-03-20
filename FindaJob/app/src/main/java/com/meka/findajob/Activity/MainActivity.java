package com.meka.findajob.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.meka.findajob.R;
import com.meka.findajob.Utils.GetSharedPref;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedKontrol();

    }

    public void  sharedKontrol(){
        SharedPreferences sharedPreferences;
        GetSharedPref getSharedPref=new GetSharedPref(MainActivity.this);
        sharedPreferences=getSharedPref.getSession();
        if(sharedPreferences.getString("id",null) == null && sharedPreferences.getString("mail",null) == null
                && sharedPreferences.getString("kadi",null) == null)
        {
            Intent intent=new Intent(MainActivity.this,SignInActivity.class);
            startActivity(intent);
            finish();
        }
    }
}