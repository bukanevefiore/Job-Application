package com.meka.findajob.Models;

public class BasvuruListeleModel {

    private String mailadres;

    private String uyeid;

    private String id;

    private String baslik;

    private String kullaniciadi;

    public String getMailadres() {
        return mailadres;
    }

    public void setMailadres(String mailadres) {
        this.mailadres = mailadres;
    }

    public String getUyeid() {
        return uyeid;
    }

    public void setUyeid(String uyeid) {
        this.uyeid = uyeid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getKullaniciadi() {
        return kullaniciadi;
    }

    public void setKullaniciadi(String kullaniciadi) {
        this.kullaniciadi = kullaniciadi;
    }

    @Override
    public String toString() {
        return "BasvuruListeleModel{" +
                "mailadres='" + mailadres + '\'' +
                ", uyeid='" + uyeid + '\'' +
                ", id='" + id + '\'' +
                ", baslik='" + baslik + '\'' +
                ", kullaniciadi='" + kullaniciadi + '\'' +
                '}';
    }
}
