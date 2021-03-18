package com.meka.findajob.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.meka.findajob.MainActivity;
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

                   Toast.makeText(SignUpActivity.this, "Lütfen tüm alanları doldurun..", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(SignUpActivity.this, response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(SignUpActivity.this, response.body().getDogrulamakodu().toString(), Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignUpActivity.this,DogrulamaActivity.class);
                    intent.putExtra("mailAdres", mail);
                    startActivity(intent);

                }else{

                    Toast.makeText(SignUpActivity.this, response.body().getText().toString(), Toast.LENGTH_LONG).show();

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

    private void showNotification() {

        /* Bildirime tıkladığımızda SecondActivity.java dosyasına gidebilmek için
           bir intent oluşturuyoruz. Bu intentle birlikte adsoyad, konu ve mesaj
           ve notifId değerlerimizi de gönderiyoruz. */
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.putExtra("adsoyad", "etAdSoyad.getText().toString()");
        resultIntent.putExtra("konu", "etKonu.getText().toString()");
        resultIntent.putExtra("mesaj", "etMesaj.getText().toString()");
        resultIntent.putExtra("notifId", NOTIF_ID);

        /* Bildirime tıklayınca iş yapabilmek için PendingIntent sınıfından bir nesne
           üretiyoruz. */
        PendingIntent pendingIntentResult = PendingIntent.getActivity(
                this, NOTIF_ID, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        /* Aşağıda setLargeIcon özelliği bizden bir Bitmap istediği için
           bu şekilde iconumuzu bitmape çeviriyoruz. */
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_notification_important_24);

        /* Bildirim oluşturabilmek için NotificationCompat.Builder ile bir nesne
           üretiyoruz. Üretilen mBuilder nesnesi ile bildirimimizin ayarlarını
           yapıyoruz. */
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("etAdSoyad.getText().toString()");
        mBuilder.setContentText("etKonu.getText().toString()");
        mBuilder.setSmallIcon(R.drawable.ic_baseline_notification_important_24);
        mBuilder.setLargeIcon(largeIcon);
        mBuilder.setAutoCancel(true);
        mBuilder.addAction(R.drawable.ic_baseline_notification_important_24, "Oku", pendingIntentResult);
        mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setContentIntent(pendingIntentResult);
        mBuilder.setSubText("Sub text buraya geliyor.");
        mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                .bigText("etMesaj.getText().toString()")
                .setBigContentTitle("etKonu.getText().toString()"));

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(uri);

        long[] v = {500, 1000};
        mBuilder.setVibrate(v);

        // NotificationManager nesnesi oluşturuyoruz.
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        // NotificationManager ile bildirimi inşa ediyoruz.
        notificationManager.notify(NOTIF_ID, mBuilder.build());

        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }
}