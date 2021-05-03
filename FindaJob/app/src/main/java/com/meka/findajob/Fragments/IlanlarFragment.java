package com.meka.findajob.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meka.findajob.Adapters.IlanAdapter;
import com.meka.findajob.Models.IlanModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.Utils.GetSharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IlanlarFragment extends Fragment {

    View view;
    private RecyclerView ilanListeleRecyclerView;
    List<IlanModel> ilanModelList;
    String kulid;
    String ilanid;
    IlanAdapter ilanAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_ilanlar, container, false);

        tanimlamalar();
        ilanListeleRequest(kulid);

        return view;
    }

    private void tanimlamalar(){

        GetSharedPref sharedPref=new GetSharedPref(getActivity());
        kulid=sharedPref.getSession().getString("id",null);
        GetSharedPref getSharedPref=new GetSharedPref(getActivity());
        ilanid=getSharedPref.getSession().getString("ilanid",null);
        ilanListeleRecyclerView=view.findViewById(R.id.ilanListeleRecyclerView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        ilanListeleRecyclerView.setLayoutManager(layoutManager);
        ilanModelList=new ArrayList<>();


    }



    public void ilanListeleRequest(String id){

        Log.i("ilanfonksiyon","ilan hata");
        Call<List<IlanModel>> request= ManagerAll.getInstance().ilanListele(id);
        request.enqueue(new Callback<List<IlanModel>>() {
            @Override
            public void onResponse(Call<List<IlanModel>> call, Response<List<IlanModel>> response) {

                Log.i("ilanistekatildi","den");
                if(response.isSuccessful()){
                    if(response.body().size() > 0) {
                        Log.i("ilan",response.body().toString());
                        ilanModelList = response.body();
                        ilanAdapter = new IlanAdapter(ilanModelList,getContext());
                        ilanListeleRecyclerView.setAdapter(ilanAdapter);
                    }
                }
                else{
                    Log.i("ilanistekhata","ilan hata");

                }
            }

            @Override
            public void onFailure(Call<List<IlanModel>> call, Throwable t) {

                Log.d("ilanHata",t.getMessage());
            }
        });
    }


}