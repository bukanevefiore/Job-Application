package com.meka.findajob.Utils;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.meka.findajob.R;

public class ChangeFragments {

    // tüm fragmentler için kullanacağımız ortak sınıf

        private Context context;

        public ChangeFragments(Context context) {
            this.context = context;
        }

        public void change(Fragment fragment){
            ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.navHostFragment,fragment,"fragment")
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();

        }

        public void changeWithParemeters(Fragment fragment,String ilanid,String kid){

            Bundle bundle=new Bundle();
            bundle.putString("ilanid",ilanid);
            bundle.putString("kid",kid);
            fragment.setArguments(bundle);

            ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.navHostFragment,fragment,"fragment")
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();

        }

    public void changeWith1Paremeters(Fragment fragment,String ilanid){

        Bundle bundle=new Bundle();
        bundle.putString("ilanid",ilanid);
        fragment.setArguments(bundle);

        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.navHostFragment,fragment,"fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

    }
