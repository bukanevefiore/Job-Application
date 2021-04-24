package com.meka.findajob.Models;

public class KullaniciBilgiModel {

    private String mailadres;

    private String kullaniciadi;

    public String getMailadres() {
        return mailadres;
    }

    public void setMailadres(String mailadres) {
        this.mailadres = mailadres;
    }

    public String getKullaniciadi() {
        return kullaniciadi;
    }

    public void setKullaniciadi(String kullaniciadi) {
        this.kullaniciadi = kullaniciadi;
    }

    @Override
    public String toString() {
        return "KullaniciBilgiModel{" +
                "mailadres='" + mailadres + '\'' +
                ", kullaniciadi='" + kullaniciadi + '\'' +
                '}';
    }
}
