package com.meka.findajob.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.meka.findajob.Models.MapModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    View view;
    MapView mapView;
    GoogleMap gm;
    Float lat=34.5f, lot=34.5f;
    String title="asd";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_map, container, false);

        mapView=view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        cek();
         return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        gm=googleMap;
        LatLng latLng=new LatLng(lat,lot);
        gm.addMarker(new MarkerOptions().position(latLng).title(title));

    }

    public void cek(){

        Call<MapModel> request= ManagerAll.getInstance().getir();
        request.enqueue(new Callback<MapModel>() {
            @Override
            public void onResponse(Call<MapModel> call, Response<MapModel> response) {
                lat=Float.parseFloat(response.body().getLat().toString());
                lot=Float.parseFloat(response.body().getLot().toString());
                title=response.body().getTitle().toString();
                mapView.getMapAsync(MapFragment.this);


            }

            @Override
            public void onFailure(Call<MapModel> call, Throwable t) {

            }
        });
    }
}