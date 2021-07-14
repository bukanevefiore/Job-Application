package com.meka.findajob.Fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meka.findajob.Adapters.IlanlarimAdapter;
import com.meka.findajob.Models.IlanModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.Utils.GetSharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IlanlarimFragment extends Fragment {

  View view;
  RecyclerView ilanlarimRecyclerView;
  List<IlanModel> list;
  String kulid;
  IlanlarimAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_ilanlarim, container, false);

        tanimlamalar();
        ilanlarimListeleRequest(kulid);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void tanimlamalar(){
        GetSharedPref getSharedPref=new GetSharedPref(getActivity());
        kulid=getSharedPref.getSession().getString("id",null);
        ilanlarimRecyclerView=view.findViewById(R.id.ilanlarimRecyclerView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        ilanlarimRecyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<>();
    }

    public void ilanlarimListeleRequest(String kulid){

        Call<List<IlanModel>> request= ManagerAll.getInstance().ilanlarimListele(kulid);
        request.enqueue(new Callback<List<IlanModel>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<IlanModel>> call, Response<List<IlanModel>> response) {
                if(response.isSuccessful()){
                    if(response.body().size()>0){
                           Log.i("ilanlarım",response.body().toString());
                        list=response.body();
                        adapter=new IlanlarimAdapter(list,getContext(),getActivity());
                        ilanlarimRecyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<IlanModel>> call, Throwable t) {

            }
        });
    }
}