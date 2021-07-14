package com.meka.findajob.Utils;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meka.findajob.Activity.MainActivity;
import com.meka.findajob.Activity.SignInActivity;
import com.meka.findajob.R;


public class CikFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_cik, container, false);

        GetSharedPref getSharedPref=new GetSharedPref(getActivity());
        getSharedPref.sharedSil();
        Intent intent=new Intent(getContext(), SignInActivity.class);
        startActivity(intent);

        return view;
    }
}