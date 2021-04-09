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
import com.meka.findajob.Adapters.EgitimAdapter;
import com.meka.findajob.Models.DeneyimEkleModel;
import com.meka.findajob.Models.DeneyimListeleModel;
import com.meka.findajob.Models.EgitimEkleModel;
import com.meka.findajob.Models.EgitimListeleModel;
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
   ImageView deneyimEkleImageView,egitimEkleImageView;
   String kulid;
   private RecyclerView deneyimRecyclerView,egitimRecyclerView ;
   List<DeneyimListeleModel> list;
   List<EgitimListeleModel> egitimList;
   DeneyimAdapter deneyimAdapter;
   EgitimAdapter egitimAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile, container, false);

        tanimlamalar();
        clicks();
        deneyimListele(kulid);
        egitimListeleRequest(kulid);

        return view;
    }

    public void tanimlamalar(){

        deneyimEkleImageView=view.findViewById(R.id.deneyimEkleImageView);
        egitimEkleImageView=view.findViewById(R.id.egitimEkleImageView);
        GetSharedPref getSharedPref=new GetSharedPref(getActivity());
        kulid=getSharedPref.getSession().getString("id",null);
        deneyimRecyclerView=view.findViewById(R.id.deneyimRecyclerView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        deneyimRecyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<>();

        egitimRecyclerView=view.findViewById(R.id.egitimRecyclerView);
        RecyclerView.LayoutManager egitimLayoutManager=new GridLayoutManager(getContext(),1);
        egitimRecyclerView.setLayoutManager(egitimLayoutManager);
        egitimList=new ArrayList<>();


    }
    public void clicks(){
        // deneyim ekle
        deneyimEkleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeneyimAlert();

            }
        });
        //egitim ekle
        egitimEkleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openEgitimAlert();
            }
        });

    }

    public void openDeneyimAlert(){

        LayoutInflater layoutInflater=this.getLayoutInflater();
        view=layoutInflater.inflate(R.layout.deneyim_ekle_alert,null);

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

    // egitim ekle alert
    public void openEgitimAlert(){

        LayoutInflater layoutInflater=this.getLayoutInflater();
        view=layoutInflater.inflate(R.layout.egitim_ekle_alert,null);

        final TextInputEditText egitimEkleOkul,egitimEkleBolum,egitimEkleBaslangic,egitimEkleBitis;
        AppCompatButton egitimEkleKaydetButon;

        egitimEkleOkul=view.findViewById(R.id.egitimEkleOkul);
        egitimEkleBolum=view.findViewById(R.id.egitimEkleBolum);
        egitimEkleBaslangic=view.findViewById(R.id.egitimEkleBaslangic);
        egitimEkleBitis=view.findViewById(R.id.egitimEkleBitis);
        egitimEkleKaydetButon=view.findViewById(R.id.egitimEkleKaydetButon);

        AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog=alert.create();

        alertDialog.show();

        egitimEkleKaydetButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String okul="",bolum="",baslangic="",bitis="";
                okul=egitimEkleOkul.getText().toString();
                bolum=egitimEkleBolum.getText().toString();
                baslangic=egitimEkleBaslangic.getText().toString();
                bitis=egitimEkleBitis.getText().toString();
                egitimEkleRequest(kulid,okul,bolum,baslangic,bitis);

                egitimEkleOkul.setText("");
                egitimEkleBolum.setText("");
                egitimEkleBaslangic.setText("");
                egitimEkleBitis.setText("");

                alertDialog.cancel();
            }
        });

    }

    // egitim ekle istek
    private void egitimEkleRequest(String kulid, String okul, String bolum, String baslangic, String bitis) {

        Call<EgitimEkleModel> request=ManagerAll.getInstance().egitimEkle(kulid, okul, bolum, baslangic, bitis);
        request.enqueue(new Callback<EgitimEkleModel>() {
            @Override
            public void onResponse(Call<EgitimEkleModel> call, Response<EgitimEkleModel> response) {

                if(response.isSuccessful()){


                    Toast.makeText(getContext(), response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EgitimEkleModel> call, Throwable t) {

            }
        });
    }

    // deneyim ekleme
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

    // deneyim listeleme
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

    // egitim listeleme
    public void egitimListeleRequest(String id){

        Call<List<EgitimListeleModel>> request=ManagerAll.getInstance().egitimListele(id);
        request.enqueue(new Callback<List<EgitimListeleModel>>() {
            @Override
            public void onResponse(Call<List<EgitimListeleModel>> call, Response<List<EgitimListeleModel>> response) {
                if(response.body().size()>0){

                    egitimList=response.body();
                    egitimAdapter=new EgitimAdapter(egitimList,getContext());
                    egitimRecyclerView.setAdapter(egitimAdapter);
                    Log.i("egitimler",response.body().toString());

                }else{

                }
            }

            @Override
            public void onFailure(Call<List<EgitimListeleModel>> call, Throwable t) {

                Log.e("egitimListeLog",t.getMessage());
            }
        });
    }
}