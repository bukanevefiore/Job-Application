package com.meka.findajob.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.meka.findajob.Models.KaydolModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText kullaniciAdiEditText,mailEditText,parolaEditText;
    Button kaydolButon;
    TextView girisButon;
    private static final int NOTIF_ID = 1;
    String kadi,ksifre,kmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tanimlamalar();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);  // edittexler arası klavye ile geçiş


    }

    public void tanimlamalar(){

        kullaniciAdiEditText=findViewById(R.id.kullaniciAdiEditText);
        mailEditText=findViewById(R.id.mailEditText);
        parolaEditText=findViewById(R.id.parolaEditText);
        kaydolButon=findViewById(R.id.kaydolButon);
        kaydolButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                kadi=kullaniciAdiEditText.getText().toString();
                kmail=mailEditText.getText().toString();
                ksifre=parolaEditText.getText().toString();
               if(kadi != "" && kmail != "" && ksifre != ""){

                   kullaniciAdiEditText.setText("");
                   mailEditText.setText("");
                   parolaEditText.setText("");

                   kayitol(kmail,kadi,ksifre);
                    //bildirim();
                   //showNotification();
               }else{

                   Toast.makeText(getApplicationContext(), "Lütfen tüm alanları doldurun..", Toast.LENGTH_LONG).show();
               }
            }
        });

        girisButon=findViewById(R.id.girisButon);
        girisButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }



    //  kaydolma servisine istek atma
    public void kayitol(String mail,String ad, String sifre){

        Call<KaydolModel> request=ManagerAll.getInstance().addUser(mail, ad, sifre);
        request.enqueue(new Callback<KaydolModel>() {
            @Override
            public void onResponse(Call<KaydolModel> call, Response<KaydolModel> response) {

                if(response.body().isTf()){

                    Log.i("dogrulama",response.body().getDogrulamakodu().toString());
                    Toast.makeText(getApplicationContext(), response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), response.body().getDogrulamakodu().toString(), Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                    intent.putExtra("mailAdres", mail);
                    intent.putExtra("kod", response.body().getDogrulamakodu());
                    startActivity(intent);

                }else{

                    Toast.makeText(getApplicationContext(), response.body().getText().toString(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<KaydolModel> call, Throwable t) {
                Log.e("Hata",t.getMessage());

            }
        });

    }

    // uygulamaya bildirim gönderme
    public void bildirim(){



        final NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final Notification.Builder builder=new Notification.Builder(SignUpActivity.this);
        builder.setContentTitle("Dogrulama Koduu");
        builder.setContentText("response.body().getDogrulamakodu().toString()");
        builder.setSmallIcon(R.drawable.ic_baseline_notification_important_24);
        builder.setAutoCancel(true);
        builder.setSound(Uri.parse(""));
        builder.setWhen(System.currentTimeMillis());
        builder.setTicker("Dogrulama Kodu");

        Intent intent=new Intent(SignUpActivity.this,DogrulamaActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(SignUpActivity.this,1,intent,0);
        builder.setContentIntent(pendingIntent);


            final Notification notification=builder.build();

            manager.notify(1,notification);



    }


}