package com.meka.findajob.Models;

public class IlanModel {

    private String aciklama;

    private String kid;

    private String id;

    private String adres;

    private String baslik;

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    @Override
    public String toString() {
        return "IlanModel{" +
                "aciklama='" + aciklama + '\'' +
                ", kid='" + kid + '\'' +
                ", id='" + id + '\'' +
                ", adres='" + adres + '\'' +
                ", baslik='" + baslik + '\'' +
                '}';
    }
}
