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
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.meka.findajob.Adapters.DeneyimAdapter;
import com.meka.findajob.Adapters.EgitimAdapter;
import com.meka.findajob.Adapters.YetenekAdapter;
import com.meka.findajob.Models.DeneyimEkleModel;
import com.meka.findajob.Models.DeneyimListeleModel;
import com.meka.findajob.Models.EgitimEkleModel;
import com.meka.findajob.Models.EgitimListeleModel;
import com.meka.findajob.Models.KullaniciBilgiModel;
import com.meka.findajob.Models.YetenekEkleModel;
import com.meka.findajob.Models.YetenekListeleModel;
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
   ImageView deneyimEkleImageView,egitimEkleImageView,yetenekEkleImageView;
   String kulid;
   private RecyclerView deneyimRecyclerView,egitimRecyclerView,yetenekRecyclerView ;
   List<DeneyimListeleModel> list;
   List<EgitimListeleModel> egitimList;
   List<YetenekListeleModel> yetenekList;
   DeneyimAdapter deneyimAdapter;
   EgitimAdapter egitimAdapter;
   YetenekAdapter yetenekAdapter;
   TextView userMailAdres,profUserName;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile, container, false);

        tanimlamalar();
        clicks();
        deneyimListele(kulid);
        egitimListeleRequest(kulid);
        yetenekListeleRequest(kulid);
        kullaniciBilgiGetirRequest(kulid);

        return view;
    }

    public void tanimlamalar(){
        // deneyim
        deneyimEkleImageView=view.findViewById(R.id.deneyimEkleImageView);
        egitimEkleImageView=view.findViewById(R.id.egitimEkleImageView);
        GetSharedPref getSharedPref=new GetSharedPref(getActivity());
        kulid=getSharedPref.getSession().getString("id",null);
        deneyimRecyclerView=view.findViewById(R.id.deneyimRecyclerView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        deneyimRecyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<>();

        // e??ititm
        egitimRecyclerView=view.findViewById(R.id.egitimRecyclerView);
        RecyclerView.LayoutManager egitimLayoutManager=new GridLayoutManager(getContext(),1);
        egitimRecyclerView.setLayoutManager(egitimLayoutManager);
        egitimList=new ArrayList<>();

        // yetenek
        yetenekRecyclerView=view.findViewById(R.id.yetenekRecyclerView);
        RecyclerView.LayoutManager yetenekLayoutManager=new GridLayoutManager(getContext(),1);
        yetenekRecyclerView.setLayoutManager(yetenekLayoutManager);
        yetenekList=new ArrayList<>();
        yetenekEkleImageView=view.findViewById(R.id.yetenekEkleImageView);

        // genel bilgiler
        userMailAdres=view.findViewById(R.id.userMailAdres);
        profUserName=view.findViewById(R.id.profUserName);


    }

    // click i??lemleri
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

        // yetenek ekle
        yetenekEkleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openYetenekAlert();
            }
        });

    }
//  deneyim alert
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

                Log.i("egitimekle",egitimEkleOkul.getText().toString());

                egitimEkleOkul.setText("");
                egitimEkleBolum.setText("");
                egitimEkleBaslangic.setText("");
                egitimEkleBitis.setText("");
                egitimListeleRequest(kulid);
                alertDialog.cancel();
            }
        });

    }

    //  yetenek alert
    public void openYetenekAlert(){

        LayoutInflater layoutInflater=this.getLayoutInflater();
        view=layoutInflater.inflate(R.layout.yetenek_ekle_alert,null);

        final TextInputEditText yetenekEkleText;
        final TextView dereceYetenekTextView;
        ProgressBar progressyetenek;
        SeekBar seekbarYetenek;
        AppCompatButton yetenekEkleKaydetButon;

        yetenekEkleText=view.findViewById(R.id.yetenekEkleText);
        dereceYetenekTextView=view.findViewById(R.id.dereceYetenekTextView);
        progressyetenek=view.findViewById(R.id.progressyetenek);
        seekbarYetenek=view.findViewById(R.id.seekbarYetenek);
        yetenekEkleKaydetButon=view.findViewById(R.id.yetenekEkleKaydetButon);

/*
        int i = 0;
        for(i=0;i<=30;i++){
            progressyetenek.setProgress(i);
        }

 */
        

        // seebar
        seekbarYetenek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressyetenek.setProgress(progress);
                dereceYetenekTextView.setText(progress+"");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // alert
        AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog=alert.create();

        alertDialog.show(); // SHOW


        yetenekEkleKaydetButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String yetenek="",yetenekderece="";
                yetenek=yetenekEkleText.getText().toString();
                yetenekderece=dereceYetenekTextView.getText().toString();
                Log.i("yetenekderece",yetenekderece);

                yetenekEkleRequest(kulid,yetenek,yetenekderece);
                yetenekEkleText.setText("");
                dereceYetenekTextView.setText("");
                yetenekListeleRequest(kulid);
                alertDialog.cancel();  // CANSEL



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

    // yetenek ekle
    public void yetenekEkleRequest(String kulid,String yetenek,String yetenekderece){

        Call<YetenekEkleModel> request=ManagerAll.getInstance().yetenekEkle(kulid, yetenek, yetenekderece);
        request.enqueue(new Callback<YetenekEkleModel>() {
            @Override
            public void onResponse(Call<YetenekEkleModel> call, Response<YetenekEkleModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<YetenekEkleModel> call, Throwable t) {

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

    public void yetenekListeleRequest(String id){

        Call<List<YetenekListeleModel>> request=ManagerAll.getInstance().yetenekListele(id);
        request.enqueue(new Callback<List<YetenekListeleModel>>() {
            @Override
            public void onResponse(Call<List<YetenekListeleModel>> call, Response<List<YetenekListeleModel>> response) {

                if(response.isSuccessful()){
                    if(response.body().size()>0){

                        yetenekList=response.body();
                        yetenekAdapter=new YetenekAdapter(yetenekList,getContext());
                        yetenekRecyclerView.setAdapter(yetenekAdapter);
                        Log.i("yetenek",response.body().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<YetenekListeleModel>> call, Throwable t) {
                Log.e("yetenekListeLog",t.getMessage());
            }
        });
    }

    // kullan??c?? bilgisi listele istek
    public void kullaniciBilgiGetirRequest(String id){

        Call<List<KullaniciBilgiModel>> request=ManagerAll.getInstance().kullaniciBilgi(id);
        request.enqueue(new Callback<List<KullaniciBilgiModel>>() {
            @Override
            public void onResponse(Call<List<KullaniciBilgiModel>> call, Response<List<KullaniciBilgiModel>> response) {

                if(response.isSuccessful()){

                    userMailAdres.setText(response.body().get(0).getMailadres().toString());
                    profUserName.setText(response.body().get(0).getKullaniciadi().toString());
                }
            }

            @Override
            public void onFailure(Call<List<KullaniciBilgiModel>> call, Throwable t) {

            }
        });
    }


}