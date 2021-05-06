package com.meka.findajob.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.meka.findajob.Models.IlanDetayModel;
import com.meka.findajob.Models.IlanDetayPaylasModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.Utils.ChangeFragments;

import java.sql.SQLTransactionRollbackException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IlanPaylasDetayFragment extends Fragment {

    View view;
    Button ilanDetayYayinlaBasvur;
    TextInputEditText ilanDetayPaylasTecrubeEditText,ilanDetayPaylasEgitimSeviyesiEditText,ilanDetayPaylasIlanBaslik,
            ilanDetayPaylasIlanAciklama,ilanDetayPaylasAdres,ilanDetayPaylasFirmaSektoruEditText,ilanDetayPaylasCalismaSekliEditText,
            ilanDetayPaylasDepartmanEditText,ilanDetayPaylasPozisyonSeviyeEditText,ilanDetayPaylasIsTanimiEditText,
            ilanDetayPaylasNitelikEditText;
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
        // ilk frgamnetten gönderilerin verileri alma
        ilanid=getArguments().getString("ilanid");
        Log.i("GöndermeBaşarılı",ilanid);
        gelenbaslik=getArguments().getString("baslik");
        gelenaciklama=getArguments().getString("aciklama");
        gelenadres=getArguments().getString("adres");
            // kriter
        ilanDetayPaylasKriterEkleImage=view.findViewById(R.id.ilanDetayPaylasKriterEkleImage);
        ilanDetayPaylasTecrubeEditText=view.findViewById(R.id.ilanDetayPaylasTecrubeEditText);
        ilanDetayPaylasEgitimSeviyesiEditText=view.findViewById(R.id.ilanDetayPaylasEgitimSeviyesiEditText);
        //  en üst başlık kısmı
        ilanDetayPaylasIlanBaslik=view.findViewById(R.id.ilanDetayPaylasIlanBaslik);
        ilanDetayPaylasIlanAciklama=view.findViewById(R.id.ilanDetayPaylasIlanAciklama);
        ilanDetayPaylasAdres=view.findViewById(R.id.ilanDetayPaylasAdres);
        ilanDetayPaylasIlanBaslik.setText(gelenbaslik);
        ilanDetayPaylasIlanAciklama.setText(gelenaciklama);
        ilanDetayPaylasAdres.setText(gelenadres);
              // pozisyon bilgi
        ilanDetayPaylasFirmaSektoruEditText=view.findViewById(R.id.ilanDetayPaylasFirmaSektoruEditText);
        ilanDetayPaylasCalismaSekliEditText=view.findViewById(R.id.ilanDetayPaylasCalismaSekliEditText);
        ilanDetayPaylasDepartmanEditText=view.findViewById(R.id.ilanDetayPaylasDepartmanEditText);
        ilanDetayPaylasPozisyonSeviyeEditText=view.findViewById(R.id.ilanDetayPaylasPozisyonSeviyeEditText);
        ilanDetayPaylasPozisyonEkleImage=view.findViewById(R.id.ilanDetayPaylasPozisyonEkleImage);

        //tanim
        ilanDetayPaylasIsTanimiEditText=view.findViewById(R.id.ilanDetayPaylasIsTanimiEditText);
        ilanDetayPaylasIsTanimiEkleImage=view.findViewById(R.id.ilanDetayPaylasIsTanimiEkleImage);
        // nitelik
        ilanDetayPaylasNitelikEditText=view.findViewById(R.id.ilanDetayPaylasNitelikEditText);
        ilanDetayPaylasNitelikEkleImage=view.findViewById(R.id.ilanDetayPaylasNitelikEkleImage);

        ilanDetayYayinlaBasvur=view.findViewById(R.id.ilanDetayYayinlaBasvur);

    }

    public void action(){
          // kriter ekle
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
           // pozisyon bilgileri ekle
        ilanDetayPaylasPozisyonEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firmasektoru="",calismasekli="",departman="",pozisyonseviyesi="";
                firmasektoru=ilanDetayPaylasFirmaSektoruEditText.getText().toString();
                calismasekli=ilanDetayPaylasCalismaSekliEditText.getText().toString();
                departman=ilanDetayPaylasDepartmanEditText.getText().toString();
                pozisyonseviyesi=ilanDetayPaylasPozisyonSeviyeEditText.getText().toString();
                if(!firmasektoru.equals("") && !calismasekli.equals("") && !departman.equals("") && !pozisyonseviyesi.equals("")){

                    ilanPozisyonBilgiPaylasRequest(ilanid,"pozisyon",firmasektoru,calismasekli,departman,pozisyonseviyesi);
                }
                else {
                    Toast.makeText(getContext(), "Fill in all fields ..", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // iş tenimi ekle
        ilanDetayPaylasIsTanimiEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String istanimi="";
                istanimi=ilanDetayPaylasIsTanimiEditText.getText().toString();
                ilanIsTanimiPaylasRequest(ilanid,"tanim",istanimi);
            }
        });

        // nitelik ekle
        ilanDetayPaylasNitelikEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nitelik="";
                nitelik=ilanDetayPaylasNitelikEditText.getText().toString();
                ilanNitelikPaylasRequest(ilanid,"nitelik",nitelik);
            }
        });

        // ilanı yayınla
        ilanDetayYayinlaBasvur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kontrol(ilanid);


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

    public void ilanPozisyonBilgiPaylasRequest(String ilanid,String text,String firmasektoru,String calismasekli,String departman,String pozisyonseviyesi){

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

    public void ilanIsTanimiPaylasRequest(String ilanid,String text,String tanim){

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

    public void ilanNitelikPaylasRequest(String ilanid,String text,String nitelik){

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