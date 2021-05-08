package com.meka.findajob.Activity.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.meka.findajob.Activity.IlanlarimNavigationActivity;
import com.meka.findajob.Activity.MainActivity;
import com.meka.findajob.Activity.SignInActivity;
import com.meka.findajob.Adapters.IlanDetayNitelikAdapter;
import com.meka.findajob.Fragments.IlanlarFragment;
import com.meka.findajob.Models.BasvuruListeleModel;
import com.meka.findajob.Models.IlanDetayModel;
import com.meka.findajob.Models.IlanDetayNitelikModel;
import com.meka.findajob.Models.IlanDetayPaylasModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.Utils.ChangeFragments;
import com.meka.findajob.Utils.GetSharedPref;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    View view;
    String ilanid;
    Activity activity;
    Button ilanDetayGuncelleButon;
    TextInputEditText ilanDetayGuncelleIlanBaslik,ilanDetayGuncelleIlanAciklama,ilanDetayGuncelleAdres,
            ilanDetayGuncelleIsTanimiEditText,ilanDetayGuncelleNitelikEditText,ilanDetayGuncelleFirmaSektoruEditText,ilanDetayGuncelleCalismaSekliEditText,
            ilanDetayGuncelleDepartmanEditText,ilanDetayGuncellePozisyonSeviyeEditText,ilanDetayGuncelleTecrubeEditText,
            ilanDetayGuncelleEgitimSeviyesiEditText;
    ImageView ilanDetayGuncelleBaslikEkleImage,ilanDetayGuncelleIsTanimiEkleImage,ilanDetayGuncelleNitelikEkleImage,ilanDetayGuncellePozisyonEkleImage,
            ilanDetayGuncelleKriterEkleImage;
    RecyclerView ilanDetayNitelikRecyclerView;
    ImageView imageGeri;
    List<IlanDetayNitelikModel> list;
    IlanDetayNitelikAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);

        tanimlamalar();
        ilanDetayGetirRequest(ilanid);
        action();

/*
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
 */


        return view;
    }

    public void tanimlamalar(){

        SharedPreferences sharedPreferences;
        GetSharedPref getSharedPref=new GetSharedPref(getActivity());
        sharedPreferences=getSharedPref.getSession();
        ilanid=sharedPreferences.getString("ilanid",null);
        Log.i("gelenhomeilanid",sharedPreferences.getString("ilanid",null));

        // kriter
        ilanDetayGuncelleKriterEkleImage=view.findViewById(R.id.ilanDetayGuncelleKriterEkleImage);
        ilanDetayGuncelleTecrubeEditText=view.findViewById(R.id.ilanDetayGuncelleTecrubeEditText);
        ilanDetayGuncelleEgitimSeviyesiEditText=view.findViewById(R.id.ilanDetayGuncelleEgitimSeviyesiEditText);
        //  en üst başlık kısmı
        ilanDetayGuncelleIlanBaslik=view.findViewById(R.id.ilanDetayGuncelleIlanBaslik);
        ilanDetayGuncelleIlanAciklama=view.findViewById(R.id.ilanDetayGuncelleIlanAciklama);
        ilanDetayGuncelleAdres=view.findViewById(R.id.ilanDetayGuncelleAdres);
        ilanDetayGuncelleBaslikEkleImage=view.findViewById(R.id.ilanDetayGuncelleBaslikEkleImage);
        // pozisyon bilgi
        ilanDetayGuncelleFirmaSektoruEditText=view.findViewById(R.id.ilanDetayGuncelleFirmaSektoruEditText);
        ilanDetayGuncelleCalismaSekliEditText=view.findViewById(R.id.ilanDetayGuncelleCalismaSekliEditText);
        ilanDetayGuncelleDepartmanEditText=view.findViewById(R.id.ilanDetayGuncelleDepartmanEditText);
        ilanDetayGuncellePozisyonSeviyeEditText=view.findViewById(R.id.ilanDetayGuncellePozisyonSeviyeEditText);
        ilanDetayGuncellePozisyonEkleImage=view.findViewById(R.id.ilanDetayGuncellePozisyonEkleImage);

        //tanim
        ilanDetayGuncelleIsTanimiEditText=view.findViewById(R.id.ilanDetayGuncelleIsTanimiEditText);
        ilanDetayGuncelleIsTanimiEkleImage=view.findViewById(R.id.ilanDetayGuncelleIsTanimiEkleImage);
        // nitelik
        ilanDetayGuncelleNitelikEditText=view.findViewById(R.id.ilanDetayGuncelleNitelikEditText);
        ilanDetayGuncelleNitelikEkleImage=view.findViewById(R.id.ilanDetayGuncelleNitelikEkleImage);

        ilanDetayGuncelleButon=view.findViewById(R.id.ilanDetayGuncelle);


    }

    public void action(){
        // kriter ekle
        ilanDetayGuncelleKriterEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tecrube="",egitimbilgisi="";
                tecrube=ilanDetayGuncelleTecrubeEditText.getText().toString();
                egitimbilgisi=ilanDetayGuncelleEgitimSeviyesiEditText.getText().toString();
                if(!tecrube.equals("") && !egitimbilgisi.equals("")) {
                    ilanKriterGuncelleRequest("kriter", ilanid, tecrube, egitimbilgisi);
                }
            }
        });
        // pozisyon bilgileri ekle
        ilanDetayGuncellePozisyonEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firmasektoru="",calismasekli="",departman="",pozisyonseviyesi="";
                firmasektoru=ilanDetayGuncelleFirmaSektoruEditText.getText().toString();
                calismasekli=ilanDetayGuncelleCalismaSekliEditText.getText().toString();
                departman=ilanDetayGuncelleDepartmanEditText.getText().toString();
                pozisyonseviyesi=ilanDetayGuncellePozisyonSeviyeEditText.getText().toString();
                if(!firmasektoru.equals("") && !calismasekli.equals("") && !departman.equals("") && !pozisyonseviyesi.equals("")){

                    ilanPozisyonBilgiGuncelleRequest(ilanid,"pozisyon",firmasektoru,calismasekli,departman,pozisyonseviyesi);
                }
                else {
                    Toast.makeText(getContext(), "Fill in all fields ..", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // iş tenimi ekle
        ilanDetayGuncelleIsTanimiEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tıklandı","tıklandı");
                String istanimi="";
                istanimi=ilanDetayGuncelleIsTanimiEditText.getText().toString();
                ilanIsTanimiGuncelleRequest(ilanid,"tanim",istanimi);
            }
        });

        // nitelik ekle
        ilanDetayGuncelleNitelikEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nitelik="";
                nitelik=ilanDetayGuncelleNitelikEditText.getText().toString();
                ilanNitelikGuncelleRequest(ilanid,"nitelik",nitelik);
            }
        });

        // ilanı yayınla
        ilanDetayGuncelleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // kontrol(ilanid);

            }
        });
    }

    // listelemeler
    public void ilanDetayGetirRequest(String ilanid){


        Call<List<IlanDetayModel>> request= ManagerAll.getInstance().ilanDetayListele(ilanid);
        Log.i("gelenbilgiler",ilanid);
        request.enqueue(new Callback<List<IlanDetayModel>>() {
            @Override
            public void onResponse(Call<List<IlanDetayModel>> call, Response<List<IlanDetayModel>> response) {
                if(response.isSuccessful()){
                    if(response.body().size()>0){

                        Log.i("gelenbilgiler",response.body().toString());
                        ilanDetayGuncelleIlanBaslik.setText(response.body().get(0).getBaslik().toString());
                        ilanDetayGuncelleIlanAciklama.setText(response.body().get(0).getAciklama().toString());
                        ilanDetayGuncelleAdres.setText(response.body().get(0).getAdres().toString());
                        ilanDetayGuncelleCalismaSekliEditText.setText(response.body().get(0).getCalismasekli().toString());
                        ilanDetayGuncelleDepartmanEditText.setText(response.body().get(0).getDepartman().toString());
                        ilanDetayGuncelleEgitimSeviyesiEditText.setText(response.body().get(0).getEgitimbilgisi().toString());
                        ilanDetayGuncelleFirmaSektoruEditText.setText(response.body().get(0).getFirmasektoru().toString());
                        ilanDetayGuncellePozisyonSeviyeEditText.setText(response.body().get(0).getPozisyonseviyesi().toString());
                        ilanDetayGuncelleIsTanimiEditText.setText(response.body().get(0).getTanim().toString());
                        ilanDetayGuncelleTecrubeEditText.setText(response.body().get(0).getTecrube().toString());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<IlanDetayModel>> call, Throwable t) {

            }
        });

        // ilan detay nitelik request
        Call<List<IlanDetayNitelikModel>> request2=ManagerAll.getInstance().ilanDetayNitelikListele(ilanid);
        request2.enqueue(new Callback<List<IlanDetayNitelikModel>>() {
            @Override
            public void onResponse(Call<List<IlanDetayNitelikModel>> call, Response<List<IlanDetayNitelikModel>> response) {
                if(response.isSuccessful()){
                    if(response.body().size()>0){
                        list=response.body();
                        adapter=new IlanDetayNitelikAdapter(list,getContext());
                        //ilanDetayNitelikRecyclerView.setAdapter(adapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<IlanDetayNitelikModel>> call, Throwable t) {

            }
        });
    }



    //  güncellemeler
    public void ilanKriterGuncelleRequest(String text,String ilanid,String tecrube,String egitimbilgisi){

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

    public void ilanPozisyonBilgiGuncelleRequest(String ilanid,String text,String firmasektoru,String calismasekli,String departman,String pozisyonseviyesi){

        Call<IlanDetayPaylasModel> request=ManagerAll.getInstance().ilanDetayPozisyonPaylas(ilanid, text, firmasektoru, calismasekli, departman, pozisyonseviyesi);
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

    public void ilanIsTanimiGuncelleRequest(String ilanid,String text,String tanim){

        Call<IlanDetayPaylasModel> request=ManagerAll.getInstance().ilanDetayIsTanimiPaylas(ilanid, text, tanim);
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

    public void ilanNitelikGuncelleRequest(String ilanid,String text,String nitelik){

        Call<IlanDetayPaylasModel> request=ManagerAll.getInstance().ilanDetayNitelikPaylas(ilanid, text, nitelik);
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

    public void kontrol(String ilanid) {

        Call<List<IlanDetayModel>> request = ManagerAll.getInstance().ilanDetayListele(ilanid);
        request.enqueue(new Callback<List<IlanDetayModel>>() {
            @Override
            public void onResponse(Call<List<IlanDetayModel>> call, Response<List<IlanDetayModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {

                        final Bundle bundle = new Bundle();
                        bundle.putString("ilanid", ilanid);
                        final NavController navController = Navigation.findNavController(getActivity(), R.id.navHostFragment);
                        navController.navigate(R.id.action_ilanPaylasDetayFragment_to_ilanlarimFragment,bundle);

                    } else {

                        Toast.makeText(getActivity(), "It is mandatory to enter all the detail information !!.", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<IlanDetayModel>> call, Throwable t) {

            }
        });
    }

}