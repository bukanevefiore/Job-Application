package com.meka.findajob.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meka.findajob.R;
import com.meka.findajob.Utils.ChangeFragments;


public class IlanPaylasDetayFragment extends Fragment {

    String ilanid;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_ilan_paylas_detay, container, false);

        ilanid=getArguments().getString("ilanid");
        Log.i("GöndermeBaşarılı",ilanid);

        return view;
    }
}