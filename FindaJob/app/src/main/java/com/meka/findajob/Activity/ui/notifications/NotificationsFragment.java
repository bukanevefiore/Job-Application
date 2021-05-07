package com.meka.findajob.Activity.ui.notifications;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.meka.findajob.Adapters.BasvurularAdapter;
import com.meka.findajob.Models.BasvuruListeleModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.Utils.GetSharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationsFragment extends Fragment {

    View root;
    private NotificationsViewModel notificationsViewModel;
    String ilanid;
    RecyclerView basvuruListeleRecyclerView;
    List<BasvuruListeleModel> list;
    BasvurularAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        root = inflater.inflate(R.layout.fragment_notifications, container, false);
       // final TextView textView = root.findViewById(R.id.text_notifications);

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        tanimlamalar();
        ilanlarimBasvurularRequest(ilanid);

        return root;
    }

    public void tanimlamalar(){

        SharedPreferences sharedPreferences;
        GetSharedPref getSharedPref=new GetSharedPref(getActivity());
        sharedPreferences=getSharedPref.getSession();
        ilanid=sharedPreferences.getString("ilanid",null);
        Log.i("gelenNotifiilanid",sharedPreferences.getString("ilanid",null));
        basvuruListeleRecyclerView=root.findViewById(R.id.basvuruListeleRecyclerView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        basvuruListeleRecyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<>();

    }

    public void ilanlarimBasvurularRequest(String ilanid){

        Call<List<BasvuruListeleModel>> request= ManagerAll.getInstance().basvuruListele(ilanid);
        request.enqueue(new Callback<List<BasvuruListeleModel>>() {
            @Override
            public void onResponse(Call<List<BasvuruListeleModel>> call, Response<List<BasvuruListeleModel>> response) {
                if(response.isSuccessful()){
                    Log.i("donus",ilanid);
                    if(response.body().size()>0){
                        Log.i("basvurular",response.body().toString());
                        list=response.body();
                        adapter=new BasvurularAdapter(list,getContext());
                        basvuruListeleRecyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BasvuruListeleModel>> call, Throwable t) {

            }
        });
    }
}