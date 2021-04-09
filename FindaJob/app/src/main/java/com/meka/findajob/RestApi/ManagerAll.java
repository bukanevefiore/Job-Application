package com.meka.findajob.RestApi;



import com.meka.findajob.Models.DeneyimEkleModel;
import com.meka.findajob.Models.DeneyimListeleModel;
import com.meka.findajob.Models.DeneyimSilModel;
import com.meka.findajob.Models.DogrulaModel;
import com.meka.findajob.Models.EgitimEkleModel;
import com.meka.findajob.Models.GirisYapModel;
import com.meka.findajob.Models.KaydolModel;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private static ManagerAll ourInstance=new ManagerAll();

    public static synchronized ManagerAll getInstance()
    {
        return ourInstance;
    }



    public Call<KaydolModel> addUser(String mail, String kadi, String sifre)
    {
        Call<KaydolModel> x=getRestApi().addUser(mail, kadi, sifre);
        return x;
    }


    public Call<DogrulaModel> aktifEt(String kod, String mail)
    {
        Call<DogrulaModel> x=getRestApi().aktifEt(kod,mail);
        return x;
    }

    public Call<GirisYapModel> girisYap(String mailadres, String sifre)
    {
        Call<GirisYapModel> x=getRestApi().girisYap(mailadres,sifre);
        return x;
    }

    public Call<DeneyimEkleModel> deneyimEkle(String kulid, String sirket,String deneyimalan,String yil)
    {
        Call<DeneyimEkleModel> x=getRestApi().deneyimEkle(kulid, sirket, deneyimalan, yil);
        return x;
    }

    public Call<List<DeneyimListeleModel>> deneyimListele(String kulid)
    {
        Call<List<DeneyimListeleModel>> x=getRestApi().deneyimListele(kulid);
        return x;
    }

    public Call<DeneyimSilModel> deneyimSil(String id)
    {
        Call<DeneyimSilModel> x=getRestApi().deneyimSil(id);
        return x;
    }

    public Call<EgitimEkleModel> egitimEkle(String id,String uni, String bolum, String baslangic, String bitis)
    {
        Call<EgitimEkleModel> x=getRestApi().egitimEkle(id,uni, bolum, baslangic, bitis);
        return x;
    }



}
