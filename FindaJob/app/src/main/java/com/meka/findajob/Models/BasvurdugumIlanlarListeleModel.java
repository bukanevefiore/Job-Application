package com.meka.findajob.Models;

public class BasvurdugumIlanlarListeleModel {

    private String durum;

    private String mailadres;

    private String aciklama;

    private String uyeid;

    private String paylasanid;

    private String id;

    private String baslik;

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getMailadres() {
        return mailadres;
    }

    public void setMailadres(String mailadres) {
        this.mailadres = mailadres;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getUyeid() {
        return uyeid;
    }

    public void setUyeid(String uyeid) {
        this.uyeid = uyeid;
    }

    public String getPaylasanid() {
        return paylasanid;
    }

    public void setPaylasanid(String paylasanid) {
        this.paylasanid = paylasanid;
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

    @Override
    public String toString() {
        return "BasvurdugumIlanlarListeleModel{" +
                "durum='" + durum + '\'' +
                ", mailadres='" + mailadres + '\'' +
                ", aciklama='" + aciklama + '\'' +
                ", uyeid='" + uyeid + '\'' +
                ", paylasanid='" + paylasanid + '\'' +
                ", id='" + id + '\'' +
                ", baslik='" + baslik + '\'' +
                '}';
    }
}
