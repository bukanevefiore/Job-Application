package com.meka.findajob.RestApi;



import com.meka.findajob.Models.BasvurdugumIlanlarListeleModel;
import com.meka.findajob.Models.BasvuruListeleModel;
import com.meka.findajob.Models.BasvuruModel;
import com.meka.findajob.Models.BasvuruOnayRedModel;
import com.meka.findajob.Models.DeneyimEkleModel;
import com.meka.findajob.Models.DeneyimListeleModel;
import com.meka.findajob.Models.IlanDetayModel;
import com.meka.findajob.Models.IlanDetayNitelikModel;
import com.meka.findajob.Models.IlanDetayPaylasModel;
import com.meka.findajob.Models.IlanModel;
import com.meka.findajob.Models.IlanPaylasModel;
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

    public Call<SilModel> deneyimSil(String id)
    {
        Call<SilModel> x=getRestApi().deneyimSil(id);
        return x;
    }

    public Call<EgitimEkleModel> egitimEkle(String id,String uni, String bolum, String baslangic, String bitis)
    {
        Call<EgitimEkleModel> x=getRestApi().egitimEkle(id,uni, bolum, baslangic, bitis);
        return x;
    }

    public Call<List<EgitimListeleModel>> egitimListele(String id)
    {
        Call<List<EgitimListeleModel>> x=getRestApi().egitimListele(id);
        return x;
    }

    public Call<SilModel> egitimSil(String id)
    {
        Call<SilModel> x=getRestApi().egitimSil(id);
        return x;
    }

    public Call<List<YetenekListeleModel>> yetenekListele(String id)
    {
        Call<List<YetenekListeleModel>> x = getRestApi().yetenekListele(id);
        return x;
    }

    public Call<YetenekEkleModel> yetenekEkle(String id, String yetenek, String yetenekDerece)
    {
        Call<YetenekEkleModel> x=getRestApi().yetenekEkle(id, yetenek, yetenekDerece);
        return x;
    }

    public Call<SilModel> yetenekSil(String id)
    {
        Call<SilModel> x=getRestApi().yetenekSil(id);
        return x;
    }

    public Call<List<KullaniciBilgiModel>> kullaniciBilgi(String id)
    {
        Call<List<KullaniciBilgiModel>> x=getRestApi().kullaniciBilgi(id);
        return x;
    }

    public Call<List<IlanModel>> ilanListele(String id)
    {
        Call<List<IlanModel>> x=getRestApi().ilanListele(id);
        return x;
    }

    public Call<List<IlanDetayModel>> ilanDetayListele(String ilanid)
    {
        Call<List<IlanDetayModel>> x=getRestApi().ilanDetayListele(ilanid);
        return x;
    }

    public Call<List<IlanDetayNitelikModel>> ilanDetayNitelikListele(String ilanid)
    {
        Call<List<IlanDetayNitelikModel>> x=getRestApi().ilanDetayNitelikListele(ilanid);
        return x;
    }

    public Call<BasvuruModel> basvuruYap(String userid, String paylasanid, String ilanid)
    {
        Call<BasvuruModel> x=getRestApi().basvuruYap(userid, paylasanid, ilanid);
        return x;
    }

    public Call<IlanPaylasModel> ilanPaylas(String kid, String baslik, String aciklama,String adres)
    {
        Call<IlanPaylasModel> x=getRestApi().ilanPaylas(kid, baslik, aciklama, adres);
        return x;
    }

    public Call<IlanDetayPaylasModel> ilanDetayKriterPaylas(String text, String ilanid, String tecrube, String egitimbilgisi)
    {
        Call<IlanDetayPaylasModel> x=getRestApi().ilanDetayKriterPaylas(text, ilanid, tecrube, egitimbilgisi);
        return x;
    }

    public Call<IlanDetayPaylasModel> ilanDetayPozisyonPaylas( String ilanid,String text, String firmasektoru, String calismasekli,
                                                               String departman,String pozisyonseviyesi)
    {
        Call<IlanDetayPaylasModel> x=getRestApi().ilanDetayPozisyonPaylas(ilanid, text, firmasektoru, calismasekli, departman, pozisyonseviyesi);
        return x;
    }

    public Call<IlanDetayPaylasModel> ilanDetayIsTanimiPaylas( String ilanid,String text, String tanim)
    {
        Call<IlanDetayPaylasModel> x=getRestApi().ilanDetayIsTanimiPaylas(ilanid, text,tanim);
        return x;
    }

    public Call<IlanDetayPaylasModel> ilanDetayNitelikPaylas( String ilanid,String text, String nitelik)
    {
        Call<IlanDetayPaylasModel> x=getRestApi().ilanDetayNitelikPaylas(ilanid, text, nitelik);
        return x;
    }

    public Call<List<IlanModel>> ilanlarimListele(String id)
    {
        Call<List<IlanModel>> x=getRestApi().ilanlarimListele(id);
        return x;
    }

    public Call<List<BasvuruListeleModel>> basvuruListele(String ilanid)
    {
        Call<List<BasvuruListeleModel>> x=getRestApi().basvuruListele(ilanid);
        return x;
    }

    public Call<BasvuruOnayRedModel> basvuruOnayla(String basvuruid)
    {
        Call<BasvuruOnayRedModel> x=getRestApi().basvuruOnayla(basvuruid);
        return x;
    }

    public Call<BasvuruOnayRedModel> basvuruRed(String basvuruid)
    {
        Call<BasvuruOnayRedModel> x=getRestApi().basvuruRed(basvuruid);
        return x;
    }

    public Call<List<BasvurdugumIlanlarListeleModel>> basvurdugumIlanlarListele(String kulid)
    {
        Call<List<BasvurdugumIlanlarListeleModel>> x=getRestApi().basvurdugumIlanlarListele(kulid);
        return x;
    }


}
