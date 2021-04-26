package com.meka.findajob.RestApi;



import com.meka.findajob.Models.DeneyimEkleModel;
import com.meka.findajob.Models.DeneyimListeleModel;
import com.meka.findajob.Models.IlanModel;
import com.meka.findajob.Models.KullaniciBilgiModel;
import com.meka.findajob.Models.SilModel;
import com.meka.findajob.Models.DogrulaModel;
import com.meka.findajob.Models.EgitimEkleModel;
import com.meka.findajob.Models.EgitimListeleModel;
import com.meka.findajob.Models.GirisYapModel;
import com.meka.findajob.Models.KaydolModel;
import com.meka.findajob.Models.YetenekEkleModel;
import com.meka.findajob.Models.YetenekListeleModel;

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
    Call<SilModel> deneyimSil(@Field("id") String id);

    @FormUrlEncoded
    @POST("/isbul/egitimekle.php")
    Call<EgitimEkleModel> egitimEkle(@Field("id") String id,@Field("uni") String uni, @Field("bolum") String bolum, @Field("baslangic") String baslangic, @Field("bitis") String bitis);

    @FormUrlEncoded
    @POST("/isbul/egitimlistele.php")
    Call<List<EgitimListeleModel>> egitimListele(@Field("kulid") String id);

    @FormUrlEncoded
    @POST("/isbul/egitimsil.php")
    Call<SilModel> egitimSil(@Field("id") String id);

    @FormUrlEncoded
    @POST("/isbul/yeteneklistele.php")
    Call<List<YetenekListeleModel>> yetenekListele(@Field("kulid") String id);

    @FormUrlEncoded
    @POST("/isbul/yetenekekle.php")
    Call<YetenekEkleModel> yetenekEkle(@Field("id") String id, @Field("yetenek") String yetenek, @Field("yetenekDerece") String yetenekDerece );

    @FormUrlEncoded
    @POST("/isbul/yeteneksil.php")
    Call<SilModel> yetenekSil(@Field("id") String id);

    @FormUrlEncoded
    @POST("/isbul/kullanicibilgigetir.php")
    Call<List<KullaniciBilgiModel>> kullaniciBilgi(@Field("kulid") String id);

    @FormUrlEncoded
    @POST("/isbul/ilanlistele.php")
    Call<List<IlanModel>> ilanListele(@Field("kid") String kid);

}
