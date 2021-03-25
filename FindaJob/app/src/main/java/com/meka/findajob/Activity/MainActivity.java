package com.meka.findajob.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.meka.findajob.R;
import com.meka.findajob.Utils.GetSharedPref;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedKontrol();
        drawerMenuKontrol();
    }

    public void drawerMenuKontrol(){

        DrawerLayout drawerLayout=findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
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