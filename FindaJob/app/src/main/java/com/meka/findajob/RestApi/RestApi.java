package com.meka.findajob.RestApi;



import com.meka.findajob.Models.DogrulaModel;
import com.meka.findajob.Models.KaydolModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {



    @FormUrlEncoded
    @POST("/isbul/kaydol.php")
    Call<KaydolModel> addUser(@Field("mail") String mail,@Field("kadi") String kadi,@Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("/isbul/kayitolaktif.php")
    Call<DogrulaModel> aktifEt(@Field("kod") String kod, @Field("mail") String mail);
}
