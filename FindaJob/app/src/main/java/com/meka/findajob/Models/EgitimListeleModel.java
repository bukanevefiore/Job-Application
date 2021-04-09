package com.meka.findajob.Models;

public class EgitimListeleModel {

    private String bitis;
    private String universite;
    private String kulid;
    private String baslangic;
    private String id;
    private String bolum;

    public String getBitis() {
        return bitis;
    }

    public void setBitis(String bitis) {
        this.bitis = bitis;
    }

    public String getUniversite() {
        return universite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }

    public String getKulid() {
        return kulid;
    }

    public void setKulid(String kulid) {
        this.kulid = kulid;
    }

    public String getBaslangic() {
        return baslangic;
    }

    public void setBaslangic(String baslangic) {
        this.baslangic = baslangic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }

    @Override
    public String toString() {
        return "EgitimListeleModel{" +
                "bitis='" + bitis + '\'' +
                ", universite='" + universite + '\'' +
                ", kulid='" + kulid + '\'' +
                ", baslangic='" + baslangic + '\'' +
                ", id='" + id + '\'' +
                ", bolum='" + bolum + '\'' +
                '}';
    }
}
