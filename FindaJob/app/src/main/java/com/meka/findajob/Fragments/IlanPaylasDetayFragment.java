package com.meka.findajob.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.meka.findajob.Models.IlanDetayPaylasModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.Utils.ChangeFragments;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IlanPaylasDetayFragment extends Fragment {

    View view;
    TextInputEditText ilanDetayPaylasTecrubeEditText,ilanDetayPaylasEgitimSeviyesiEditText,ilanDetayPaylasIlanBaslik,
            ilanDetayPaylasIlanAciklama,ilanDetayPaylasAdres;
    ImageView ilanDetayPaylasKriterEkleImage,ilanDetayPaylasPozisyonEkleImage,ilanDetayPaylasNitelikEkleImage,ilanDetayPaylasIsTanimiEkleImage,
            ilanDetayPaylasBaslikEkleImage;
    String ilanid,gelenbaslik,gelenaciklama,gelenadres;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_ilan_paylas_detay, container, false);

        tanimlamalar();
        action();

        return view;
    }

    public void tanimlamalar(){
        ilanid=getArguments().getString("ilanid");
        Log.i("GöndermeBaşarılı",ilanid);
        gelenbaslik=getArguments().getString("baslik");
        gelenaciklama=getArguments().getString("aciklama");
        gelenadres=getArguments().getString("adres");

        ilanDetayPaylasKriterEkleImage=view.findViewById(R.id.ilanDetayPaylasKriterEkleImage);
        ilanDetayPaylasTecrubeEditText=view.findViewById(R.id.ilanDetayPaylasTecrubeEditText);
        ilanDetayPaylasEgitimSeviyesiEditText=view.findViewById(R.id.ilanDetayPaylasEgitimSeviyesiEditText);
        ilanDetayPaylasIlanBaslik=view.findViewById(R.id.ilanDetayPaylasIlanBaslik);
        ilanDetayPaylasIlanAciklama=view.findViewById(R.id.ilanDetayPaylasIlanAciklama);
        ilanDetayPaylasAdres=view.findViewById(R.id.ilanDetayPaylasAdres);

        ilanDetayPaylasIlanBaslik.setText(gelenbaslik);
        ilanDetayPaylasIlanAciklama.setText(gelenaciklama);
        ilanDetayPaylasAdres.setText(gelenadres);


    }

    public void action(){

        ilanDetayPaylasKriterEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tecrube="",egitimbilgisi="";
                tecrube=ilanDetayPaylasTecrubeEditText.getText().toString();
                egitimbilgisi=ilanDetayPaylasEgitimSeviyesiEditText.getText().toString();
                if(!tecrube.equals("") && !egitimbilgisi.equals("")) {
                    ilanKriterPaylasRequest("kriter", ilanid, tecrube, egitimbilgisi);
                }
            }
        });

    }

    public void ilanKriterPaylasRequest(String text,String ilanid,String tecrube,String egitimbilgisi){

        Call<IlanDetayPaylasModel> request= ManagerAll.getInstance().ilanDetayKriterPaylas(text, ilanid, tecrube, egitimbilgisi);
        request.enqueue(new Callback<IlanDetayPaylasModel>() {
            @Override
            public void onResponse(Call<IlanDetayPaylasModel> call, Response<IlanDetayPaylasModel> response) {

                if(response.isSuccessful()){
                    Toast.makeText(getContext(), response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IlanDetayPaylasModel> call, Throwable t) {

            }
        });
    }
}