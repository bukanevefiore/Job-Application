package com.meka.findajob.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.meka.findajob.Models.DogrulaModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogrulamaActivity extends AppCompatActivity {

    TextInputEditText mailDogrulaText,dogrulamaKoduText;
    Button dogrulaButon;
    Intent intent=new Intent();
    String maildogrula,dogrulamaKodu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogrulama);
    }

    public void tanimlamalar(){

        mailDogrulaText=findViewById(R.id.mailDogrulaText);
        dogrulamaKoduText=findViewById(R.id.dogrulamaKoduText);
        dogrulaButon=findViewById(R.id.dogrulaButon);
        dogrulamaKoduText.setText(intent.getStringExtra("mailAdres"));
        dogrulaButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                maildogrula=mailDogrulaText.getText().toString();
                dogrulamaKodu=dogrulamaKoduText.getText().toString();

            }
        });

    }

    public void kullaniciDogrula(String kod,String mail){

       Call<DogrulaModel> request=ManagerAll.getInstance().aktifEt(kod, mail);
       request.enqueue(new Callback<DogrulaModel>() {
           @Override
           public void onResponse(Call<DogrulaModel> call, Response<DogrulaModel> response) {

               if(response.body().isTf()){

                   Toast.makeText(DogrulamaActivity.this, response.body().getResult().toString(), Toast.LENGTH_SHORT).show();
               }else{

                   Toast.makeText(DogrulamaActivity.this, response.body().getResult().toString(), Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<DogrulaModel> call, Throwable t) {
               Log.e("dogrulamaistekhata",t.getMessage());

           }
       });


    }
}