package com.meka.findajob.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meka.findajob.Adapters.BasvurdugumIlanlarAdapter;
import com.meka.findajob.Models.BasvurdugumIlanlarListeleModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.Utils.GetSharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasvurdugumIlanlarFragment extends Fragment {

    View view;
    RecyclerView basvurdugumIlanlarListeleRecyclerView;
    List<BasvurdugumIlanlarListeleModel> list;
    BasvurdugumIlanlarAdapter adapter;
    String kulid;

    /*
    // TODO: Rename and change types and number of parameters
    public static BasvurdugumIlanlarFragment newInstance(String param1, String param2) {
        BasvurdugumIlanlarFragment fragment = new BasvurdugumIlanlarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_basvurdugum_ilanlar, container, false);

        tanimlamalar();
        basvurdugumIlanlarListeleRequest(kulid);

        return view;
    }

    public void tanimlamalar(){

        GetSharedPref getSharedPref=new GetSharedPref(getActivity());
        kulid=getSharedPref.getSession().getString("id",null);
        basvurdugumIlanlarListeleRecyclerView=view.findViewById(R.id.basvurdugumIlanlarListeleRecyclerView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        basvurdugumIlanlarListeleRecyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<>();

    }


    public void basvurdugumIlanlarListeleRequest(String kulid){

        Call<List<BasvurdugumIlanlarListeleModel>> request= ManagerAll.getInstance().basvurdugumIlanlarListele(kulid);
        request.enqueue(new Callback<List<BasvurdugumIlanlarListeleModel>>() {
            @Override
            public void onResponse(Call<List<BasvurdugumIlanlarListeleModel>> call, Response<List<BasvurdugumIlanlarListeleModel>> response) {
                if(response.isSuccessful()){
                    if(response.body().size()>0){

                        Log.i("basvurularım",response.body().toString());
                        list=response.body();
                        adapter=new BasvurdugumIlanlarAdapter(list,getContext());
                        basvurdugumIlanlarListeleRecyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BasvurdugumIlanlarListeleModel>> call, Throwable t) {

            }
        });

    }


}