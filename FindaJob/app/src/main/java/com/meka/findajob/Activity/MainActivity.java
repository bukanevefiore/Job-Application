package com.meka.findajob.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.meka.findajob.R;
import com.meka.findajob.Utils.GetSharedPref;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedKontrol();
        drawerMenuKontrol();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);  // edittexler arası klavye ile geçiş
    }

    public void drawerMenuKontrol(){

        DrawerLayout drawerLayout=findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView=findViewById(R.id.navigationView);
        // belirlediğimiz iconların kullanılması için standartı null yapma
        navigationView.setItemIconTintList(null);

        NavController navController= Navigation.findNavController(this,R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView,navController);

        final TextView textTitle=findViewById(R.id.textTitle);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                textTitle.setText(destination.getLabel());
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