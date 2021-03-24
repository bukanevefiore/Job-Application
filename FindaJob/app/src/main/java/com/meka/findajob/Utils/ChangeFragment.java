package com.meka.findajob.Utils;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meka.findajob.R;


public class ChangeFragment extends Fragment {

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_change, container, false);

        return view;
    }

    public void change(Fragment fragment){

        ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.mai,fragment,"fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void changeWithParemeters(Fragment fragment,String Id){

        Bundle bundle=new Bundle();
        bundle.putString("Id",Id);
        fragment.setArguments(bundle);
        ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction()
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}