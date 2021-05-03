package com.meka.findajob.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.meka.findajob.Models.IlanDetayModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IlanDetayFragment extends Fragment {

    View view;
    String ilanId;
    TextView ilanDetayIlanBaslik,ilanDetayIlanAciklama,ilanDetayAdres,ilanDetayIsTanimi,ilanDetayFirmaSektoru,
            ilanDetayCalısmaSekli,ilanDetayDepartman,ilanDetayPozisyon,ilanDetayTecrube,ilanDetayEgitimSeviyesi;
    Button ilanDetayButtonBasvur,ilanDetayButtonFavoriyeAl;
    RecyclerView ilanDetayNitelikRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ilan_detay, container, false);

        return view;
    }

    public void tanimlamalar(){
        ilanId=getArguments().getString("ilanid").toString();
        ilanDetayButtonFavoriyeAl=view.findViewById(R.id.ilanDetayButtonFavoriyeAl);
        ilanDetayButtonBasvur=view.findViewById(R.id.ilanDetayButtonBasvur);
        ilanDetayNitelikRecyclerView=view.findViewById(R.id.ilanDetayNitelikRecyclerView);
        ilanDetayIlanBaslik=view.findViewById(R.id.ilanDetayIlanBaslik);
        ilanDetayIlanAciklama=view.findViewById(R.id.ilanDetayIlanAciklama);
        ilanDetayAdres=view.findViewById(R.id.ilanDetayAdres);
        ilanDetayIsTanimi=view.findViewById(R.id.ilanDetayIsTanimi);
        ilanDetayFirmaSektoru=view.findViewById(R.id.ilanDetayFirmaSektoru);
        ilanDetayCalısmaSekli=view.findViewById(R.id.ilanDetayCalısmaSekli);
        ilanDetayDepartman=view.findViewById(R.id.ilanDetayDepartman);
        ilanDetayPozisyon=view.findViewById(R.id.ilanDetayPozisyon);
        ilanDetayTecrube=view.findViewById(R.id.ilanDetayTecrube);
        ilanDetayEgitimSeviyesi=view.findViewById(R.id.ilanDetayEgitimSeviyesi);

    }

    public void ilanDetayRequest(String ilanid){

        Call<List<IlanDetayModel>> request= ManagerAll.getInstance().ilanDetayListele(ilanid);
        request.enqueue(new Callback<List<IlanDetayModel>>() {
            @Override
            public void onResponse(Call<List<IlanDetayModel>> call, Response<List<IlanDetayModel>> response) {

            }

            @Override
            public void onFailure(Call<List<IlanDetayModel>> call, Throwable t) {

            }
        });
    }
}