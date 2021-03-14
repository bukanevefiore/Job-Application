package com.meka.findajob.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
    SignInButton googleGiris;
    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN=100;
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
                kayitol(kadi,ksifre,kmail);
                // bildirim();
            }
        });



       //  google ile oturum açma
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        googleGiris=findViewById(R.id.googleGiris);
        googleGiris.setSize(SignInButton.SIZE_STANDARD);
        googleGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });




    }

    //  google ile otuurm açma
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                Toast.makeText(this, "Kullanıcı Mail:"+personEmail, Toast.LENGTH_LONG).show();
            }

            startActivity(new Intent(SignUpActivity.this,MainActivity.class));

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("message" , e.toString());

        }
    }

    //  kaydolma servisine istek atma
    public void kayitol(String ad, String sifre,String mail){

        Call<KaydolModel> request=ManagerAll.getInstance().addUser(ad, sifre, mail);
        request.enqueue(new Callback<KaydolModel>() {
            @Override
            public void onResponse(Call<KaydolModel> call, Response<KaydolModel> response) {

                Log.i("dogrulama",response.body().getDogrulamakodu().toString());
                if(response.body().isTf()){

                    Toast.makeText(SignUpActivity.this, response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SignUpActivity.this,DogrulamaActivity.class);
                    intent.putExtra("mailAdres", mail);
                    startActivity(intent);

                }else{


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

        try {
            final Notification notification=builder.build();

            manager.notify(1,notification);



        }catch (Exception e){

            Log.e("bildirimm",e.getMessage());
        }


    }
}