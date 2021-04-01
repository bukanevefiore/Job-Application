package com.meka.findajob.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.meka.findajob.Adapters.DeneyimAdapter;
import com.meka.findajob.Models.DeneyimEkleModel;
import com.meka.findajob.Models.DeneyimListeleModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.Utils.GetSharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {


   View view;
   ImageView deneyimEkleImageView;
   String kulid;
   private RecyclerView deneyimRecyclerView;
   List<DeneyimListeleModel> list;
   DeneyimAdapter deneyimAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile, container, false);

        tanimlamalar();
        clicks();
        deneyimListele(kulid);

        return view;
    }

    public void tanimlamalar(){

        deneyimEkleImageView=view.findViewById(R.id.deneyimEkleImageView);
        GetSharedPref getSharedPref=new GetSharedPref(getActivity());
        kulid=getSharedPref.getSession().getString("id",null);
        deneyimRecyclerView=view.findViewById(R.id.deneyimRecyclerView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        deneyimRecyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<>();

    }
    public void clicks(){



        deneyimEkleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeneyimAlert();

            }
        });
    }

    public void openDeneyimAlert(){

        LayoutInflater layoutInflater=this.getLayoutInflater();
        view=layoutInflater.inflate(R.layout.deneyim_ekle_layout,null);

        final TextInputEditText deneyimEkleSirket,deneyimEkleAlan,deneyimEkleYil;
        AppCompatButton deneyimEkleKaydetButon;

        deneyimEkleSirket=view.findViewById(R.id.deneyimEkleSirket);
        deneyimEkleAlan=view.findViewById(R.id.deneyimEkleAlan);
        deneyimEkleYil=view.findViewById(R.id.deneyimEkleYil);
        deneyimEkleKaydetButon=view.findViewById(R.id.deneyimEkleKaydetButon);

        AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog=alert.create();

        alertDialog.show(); // SHOW


        deneyimEkleKaydetButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sirket="",deneyimalan="",yil="";
                sirket=deneyimEkleSirket.getText().toString();
                deneyimalan=deneyimEkleAlan.getText().toString();
                yil=deneyimEkleYil.getText().toString();

                deneyimEkleRequest(kulid,sirket,deneyimalan,yil);
                deneyimEkleAlan.setText("");
                deneyimEkleSirket.setText("");
                deneyimEkleYil.setText("");
                deneyimListele(kulid);
                alertDialog.cancel();  // CANSEL


            }
        });



    }

    public void deneyimEkleRequest(String kulid,String sirket,String deneyimalan,String yil){

        Call<DeneyimEkleModel> request= ManagerAll.getInstance().deneyimEkle(kulid, sirket, deneyimalan, yil);
        request.enqueue(new Callback<DeneyimEkleModel>() {
            @Override
            public void onResponse(Call<DeneyimEkleModel> call, Response<DeneyimEkleModel> response) {

                if(response.body().isTf()){

                    Toast.makeText(getContext(), response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeneyimEkleModel> call, Throwable t) {

            }
        });
    }

    public void deneyimListele(String kulidd){
        Call<List<DeneyimListeleModel>> request=ManagerAll.getInstance().deneyimListele(kulidd);
        request.enqueue(new Callback<List<DeneyimListeleModel>>() {
            @Override
            public void onResponse(Call<List<DeneyimListeleModel>> call, Response<List<DeneyimListeleModel>> response) {
                if(response.isSuccessful()){
                    if(response.body().size()>0) {
                        list = response.body();
                        deneyimAdapter=new DeneyimAdapter(list,getContext());
                        deneyimRecyclerView.setAdapter(deneyimAdapter);
                        Log.i("deneyimler", response.body().toString());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<DeneyimListeleModel>> call, Throwable t) {

            }
        });
    }
}