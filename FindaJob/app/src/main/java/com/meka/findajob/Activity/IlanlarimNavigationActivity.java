package com.meka.findajob.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.meka.findajob.Activity.ui.home.HomeFragment;
import com.meka.findajob.Activity.ui.notifications.NotificationsFragment;
import com.meka.findajob.Fragments.IlanlarimFragment;
import com.meka.findajob.R;
import com.meka.findajob.Utils.ChangeFragments;
import com.meka.findajob.Utils.GetSharedPref;

public class IlanlarimNavigationActivity extends AppCompatActivity {

    String ilanid;
    ChangeFragments changeFragments;
    ImageView imageGeriIlanlarim;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim_navigation);

        tanimlamalar();
        BottomNav();


    }
/*
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            changeFragments.change2(new HomeFragment(),ilanid);
                            break;
                        case R.id.navigation_notifications:
                            changeFragments.change2(new NotificationsFragment(),ilanid);
                            break;
                    }
                    return false;
                }
            };

 */

    public void tanimlamalar(){
        Bundle bundle=getIntent().getExtras();
        ilanid=bundle.getString("ilanid");
        Log.i("idgoster",ilanid);

        GetSharedPref getSharedPref=new GetSharedPref(IlanlarimNavigationActivity.this);
        getSharedPref.setSession2(ilanid);

        imageGeriIlanlarim=findViewById(R.id.imageGeriIlanlarim);
        imageGeriIlanlarim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ChangeFragments changeFragments=new ChangeFragments(context);
                //changeFragments.change(new IlanlarimFragment());
            }
        });

    }

    public void BottomNav(){


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_notifications)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavGraph navInflater = navController.getNavInflater().inflate(R.navigation.mobile_navigation);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);




    }


}