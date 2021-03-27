package com.meka.findajob.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.meka.findajob.Models.DeneyimEkleModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.Utils.GetSharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {


   View view;
   ImageView deneyimEkleImageView;
   String kulid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile, container, false);

        tanimlamalar();
        clicks();

        return view;
    }

    public void tanimlamalar(){

        deneyimEkleImageView=view.findViewById(R.id.deneyimEkleImageView);

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

        TextInputEditText deneyimEkleSirket,deneyimEkleAlan,deneyimEkleYil;
        AppCompatButton deneyimEkleKaydetButon;

        deneyimEkleSirket=view.findViewById(R.id.deneyimEkleSirket);
        deneyimEkleAlan=view.findViewById(R.id.deneyimEkleAlan);
        deneyimEkleYil=view.findViewById(R.id.deneyimEkleYil);
        deneyimEkleKaydetButon=view.findViewById(R.id.deneyimEkleKaydetButon);

        String sirket,deneyimalan,yil;
        sirket=deneyimEkleSirket.getText().toString();
        deneyimalan=deneyimEkleAlan.getText().toString();
        yil=deneyimEkleYil.getText().toString();
        GetSharedPref getSharedPref=new GetSharedPref(getActivity());
        kulid=getSharedPref.getSession().getString("id",null);

        deneyimEkleKaydetButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deneyimEkleRequest(kulid,sirket,deneyimalan,yil);
                deneyimEkleAlan.setText("");
                deneyimEkleSirket.setText("");
                deneyimEkleYil.setText("");
            }
        });


        AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog=alert.create();

        alertDialog.show();
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
}