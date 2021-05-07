package com.meka.findajob.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import com.meka.findajob.Models.GirisYapModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.Utils.GetSharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    TextInputEditText mailSignInText,parolaSignInText;
    AppCompatButton girisYapButon;
    SignInButton googleGiris;
    GoogleSignInClient mGoogleSignInClient;
    TextView yeniHesapButon;
    private static int RC_SIGN_IN=100;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        tanimlamalar();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);  // edittexler arası klavye ile geçiş
    }

    public void tanimlamalar(){

        mailSignInText=findViewById(R.id.mailSignInText);
        parolaSignInText=findViewById(R.id.parolaSignInText);
        girisYapButon=findViewById(R.id.girisYapButon);
        girisYapButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dialog açma
                progressDialog = new ProgressDialog(SignInActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent);

                // request için değişkenler tanımlama
                String mail,sifre;
                mail=mailSignInText.getText().toString();
                sifre=parolaSignInText.getText().toString();
                mailSignInText.setText("");
                parolaSignInText.setText("");

                if(mail !="" && sifre != "") {
                    Log.i("konrol1",mail+ sifre);
                    girisYap(mail, sifre);

                }else{
                    Toast.makeText(getApplicationContext(), "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
                }

            }

        });
        // dialog cansel
       // progressDialog.cancel();
        // yeni hesap
        yeniHesapButon=findViewById(R.id.yeniHesapButon);
        yeniHesapButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
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

    @Override
    public void onBackPressed() {
        progressDialog.dismiss();
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

            startActivity(new Intent(SignInActivity.this, MainActivity.class));

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("message" , e.toString());

        }
    }

    public void girisYap(String mail,String sifre){


        Call<GirisYapModel> request= ManagerAll.getInstance().girisYap(mail, sifre);
        request.enqueue(new Callback<GirisYapModel>() {
            @Override
            public void onResponse(Call<GirisYapModel> call, Response<GirisYapModel> response) {

                Log.i("kontroll", "onResponse: dogruuu");
                if(response.body().isTf()){
                    Log.i("kontroll", "onResponse: dogruuu");
                    Toast.makeText(getApplicationContext(), response.body().getText().toString(), Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignInActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                    GetSharedPref getSharedPref=new GetSharedPref(SignInActivity.this);
                    getSharedPref.setSession(response.body().getId().toString(),response.body().getMailadres().toString(),
                            response.body().getKadi().toString());

                }else{

                    Toast.makeText(getApplicationContext(), response.body().getText().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GirisYapModel> call, Throwable t) {
                Log.e("loginhata",t.getMessage());
                Toast.makeText(getApplicationContext(), "internet hatası", Toast.LENGTH_LONG).show();

            }
        });
    }
}