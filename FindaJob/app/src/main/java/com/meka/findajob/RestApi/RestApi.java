package com.meka.findajob.RestApi;



import com.meka.findajob.Models.DeneyimEkleModel;
import com.meka.findajob.Models.DeneyimListeleModel;
import com.meka.findajob.Models.DeneyimSilModel;
import com.meka.findajob.Models.DogrulaModel;
import com.meka.findajob.Models.GirisYapModel;
import com.meka.findajob.Models.KaydolModel;

import java.util.List;

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

    @FormUrlEncoded
    @POST("/isbul/girisyap.php")
    Call<GirisYapModel> girisYap(@Field("mailadres") String mailadres, @Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("/isbul/deneyimekle.php")
    Call<DeneyimEkleModel> deneyimEkle(@Field("id") String id, @Field("sirket") String sirket, @Field("deneyimalan") String deneyimalan, @Field("yil") String yil);

    @FormUrlEncoded
    @POST("/isbul/deneyimlistele.php")
    Call<List<DeneyimListeleModel>> deneyimListele(@Field("kulid") String kulid);

    @FormUrlEncoded
    @POST("/isbul/deneyimsil.php")
    Call<DeneyimSilModel> deneyimSil(@Field("id") String id);


}
