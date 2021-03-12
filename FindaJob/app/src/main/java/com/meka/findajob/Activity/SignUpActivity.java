package com.meka.findajob.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.meka.findajob.Models.KaydolModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    EditText kullaniciAdiEditText,mailEditText,parolaEditText;
    Button kaydolButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tanimlamalar();

    }

    public void tanimlamalar(){

        kullaniciAdiEditText=findViewById(R.id.kullaniciAdiEditText);
        mailEditText=findViewById(R.id.mailEditText);
        parolaEditText=findViewById(R.id.parolaEditText);
        kaydolButon=findViewById(R.id.kaydolButon);

        kaydolButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kadi,ksifre,kmail;
                kadi=kullaniciAdiEditText.getText().toString();
                kmail=mailEditText.getText().toString();
                ksifre=parolaEditText.getText().toString();
                kayitol(kadi,ksifre,kmail);
            }
        });
    }

    public void kayitol(String ad, String sifre,String mail){

        Call<KaydolModel> request=ManagerAll.getInstance().addUser(ad, sifre, mail);
        request.enqueue(new Callback<KaydolModel>() {
            @Override
            public void onResponse(Call<KaydolModel> call, Response<KaydolModel> response) {

                if(response.body().isTf()){

                    Toast.makeText(SignUpActivity.this, response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KaydolModel> call, Throwable t) {
                Log.e("Hata",t.getMessage());

            }
        });

    }
}