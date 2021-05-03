package com.meka.findajob.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.meka.findajob.Adapters.IlanDetayNitelikAdapter;
import com.meka.findajob.Models.IlanDetayModel;
import com.meka.findajob.Models.IlanDetayNitelikModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.Utils.ChangeFragments;

import java.util.ArrayList;
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
    ImageView imageGeri;
    List<IlanDetayNitelikModel> list;
    IlanDetayNitelikAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ilan_detay, container, false);

        tanimlamalar();
        ilanDetayRequest(ilanId);

        return view;
    }

    public void tanimlamalar(){
        imageGeri=view.findViewById(R.id.imageGeri);
        ilanId=getArguments().getString("ilanid").toString();
        ilanDetayButtonFavoriyeAl=view.findViewById(R.id.ilanDetayButtonFavoriyeAl);
        ilanDetayButtonBasvur=view.findViewById(R.id.ilanDetayButtonBasvur);
        ilanDetayNitelikRecyclerView=view.findViewById(R.id.ilanDetayNitelikRecyclerView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        ilanDetayNitelikRecyclerView.setLayoutManager(layoutManager);
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
        imageGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragments changeFragment=new ChangeFragments(getContext());
                changeFragment.change(new IlanlarFragment());
            }
        });

        list=new ArrayList<>();



    }

    public void ilanDetayRequest(String ilanid){

        Call<List<IlanDetayModel>> request= ManagerAll.getInstance().ilanDetayListele(ilanid);
        request.enqueue(new Callback<List<IlanDetayModel>>() {
            @Override
            public void onResponse(Call<List<IlanDetayModel>> call, Response<List<IlanDetayModel>> response) {
                if(response.isSuccessful()){
                    if(response.body().size()>0){

                        ilanDetayIlanBaslik.setText(response.body().get(0).getBaslik().toString());
                        ilanDetayIlanAciklama.setText(response.body().get(0).getAciklama().toString());
                        ilanDetayAdres.setText(response.body().get(0).getAdres().toString());
                        ilanDetayCalısmaSekli.setText(response.body().get(0).getCalismasekli().toString());
                        ilanDetayDepartman.setText(response.body().get(0).getDepartman().toString());
                        ilanDetayEgitimSeviyesi.setText(response.body().get(0).getEgitimbilgisi().toString());
                        ilanDetayFirmaSektoru.setText(response.body().get(0).getFirmasektoru().toString());
                        ilanDetayPozisyon.setText(response.body().get(0).getPozisyonseviyesi().toString());
                        ilanDetayIsTanimi.setText(response.body().get(0).getTanim().toString());
                        ilanDetayTecrube.setText(response.body().get(0).getTecrube().toString());
                    }else{
                        ChangeFragments changeFragment=new ChangeFragments(getContext());
                        changeFragment.change(new IlanlarFragment());
                        Toast.makeText(getActivity(), "There are no details of the announcement.", Toast.LENGTH_SHORT).show();
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
                        ilanDetayNitelikRecyclerView.setAdapter(adapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<IlanDetayNitelikModel>> call, Throwable t) {

            }
        });
    }
}