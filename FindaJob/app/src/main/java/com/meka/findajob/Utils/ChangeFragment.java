package com.meka.findajob.Utils;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meka.findajob.R;


public class ChangeFragment  {

   private Context context;
   public ChangeFragment(Context context){this.context=context; }
/*
    public void change(Fragment fragment){

        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.mai,fragment,"fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

 */

    public void changeWithParemeters(Fragment fragment,String Id){

        Bundle bundle=new Bundle();
        bundle.putString("Id",Id);
        fragment.setArguments(bundle);
        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}