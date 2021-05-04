package com.meka.findajob.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.meka.findajob.Models.IlanDetayModel;
import com.meka.findajob.Models.IlanPaylasModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.Utils.ChangeFragments;
import com.meka.findajob.Utils.GetSharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IlanPaylasFragment extends Fragment {
    View view;
    TextInputEditText ilanPaylasBaslikEditText,ilanPaylasAciklamaEditText,ilanPaylasAdresEditText;
    AppCompatButton ilanPaylasButon;
    ChangeFragments changeFragments;
    String userid;
    GetSharedPref getSharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_ilan_paylas, container, false);

        tanimlamalar();
        action();



        return view;
    }

    public void tanimlamalar(){
        changeFragments=new ChangeFragments(getContext());
        getSharedPref=new GetSharedPref(getActivity());
        userid=getSharedPref.getSession().getString("id",null);
        ilanPaylasBaslikEditText=view.findViewById(R.id.ilanPaylasBaslikEditText);
        ilanPaylasAciklamaEditText=view.findViewById(R.id.ilanPaylasAciklamaEditText);
        ilanPaylasAdresEditText=view.findViewById(R.id.ilanPaylasAdresEditText);
        ilanPaylasButon=view.findViewById(R.id.ilanPaylasButon);

    }

    public void action(){

        ilanPaylasButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String baslik="",aciklama="",adres="";
                baslik=ilanPaylasBaslikEditText.getText().toString();
                aciklama=ilanPaylasAciklamaEditText.getText().toString();
                adres=ilanPaylasAdresEditText.getText().toString();

                if(!baslik.equals("") && !aciklama.equals("") && !adres.equals("")) {
                    ilanPaylasBaslikEditText.setText("");
                    ilanPaylasAciklamaEditText.setText("");
                    ilanPaylasAdresEditText.setText("");
                    ilanPaylasRequest(userid, baslik, aciklama, adres);
                    changeFragments.changeWith1Paremeters(new IlanPaylasDetayFragment(),"ilanid");
                }
                else{
                    Toast.makeText(getContext(), "Tüm alanları doldurunuz..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void ilanPaylasRequest(String kid,String baslik,String aciklama,String adres){

        Call<IlanPaylasModel> request= ManagerAll.getInstance().ilanPaylas(kid, baslik, aciklama, adres);
        request.enqueue(new Callback<IlanPaylasModel>() {
            @Override
            public void onResponse(Call<IlanPaylasModel> call, Response<IlanPaylasModel> response) {
                if(response.isSuccessful()){
                    if(response.body().isTf()){


                    }else{
                        Toast.makeText(getContext(), response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<IlanPaylasModel> call, Throwable t) {

            }
        });
    }
}