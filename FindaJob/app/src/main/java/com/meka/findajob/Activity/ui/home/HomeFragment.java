package com.meka.findajob.Activity.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.meka.findajob.Activity.IlanlarimNavigationActivity;
import com.meka.findajob.Activity.MainActivity;
import com.meka.findajob.Activity.SignInActivity;
import com.meka.findajob.Models.BasvuruListeleModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.Utils.GetSharedPref;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    String ilanid;
    Activity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        tanimlamalar();


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    public void tanimlamalar(){

        SharedPreferences sharedPreferences;
        GetSharedPref getSharedPref=new GetSharedPref(getActivity());
        sharedPreferences=getSharedPref.getSession();
        ilanid=sharedPreferences.getString("ilanid",null);
        Log.i("gelenhomeilanid",sharedPreferences.getString("ilanid",null));

    }


}