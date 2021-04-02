package com.meka.findajob.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.meka.findajob.R;

public class AlertDialog {

    View view;
    Context context;

    public void openDeneyimAlert(){

        LayoutInflater layoutInflater = null;
        view=layoutInflater.inflate(R.layout.deneyim_ekle_layout,null);

        final TextInputEditText deneyimEkleSirket,deneyimEkleAlan,deneyimEkleYil;
        AppCompatButton deneyimEkleKaydetButon;

        deneyimEkleSirket=view.findViewById(R.id.deneyimEkleSirket);
        deneyimEkleAlan=view.findViewById(R.id.deneyimEkleAlan);
        deneyimEkleYil=view.findViewById(R.id.deneyimEkleYil);
        deneyimEkleKaydetButon=view.findViewById(R.id.deneyimEkleKaydetButon);

        android.app.AlertDialog.Builder alert=new android.app.AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final android.app.AlertDialog alertDialog=alert.create();

        alertDialog.show(); // SHOW


        deneyimEkleKaydetButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sirket="",deneyimalan="",yil="";
                sirket=deneyimEkleSirket.getText().toString();
                deneyimalan=deneyimEkleAlan.getText().toString();
                yil=deneyimEkleYil.getText().toString();


                deneyimEkleAlan.setText("");
                deneyimEkleSirket.setText("");
                deneyimEkleYil.setText("");

                alertDialog.cancel();  // CANSEL


            }
        });



    }





}
