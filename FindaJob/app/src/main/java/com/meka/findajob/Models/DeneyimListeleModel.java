package com.meka.findajob.Models;

public class DeneyimListeleModel {

    private String sirket;

    private String kulid;

    private String deneyimalan;

    private String id;

    private String yil;

    public String getSirket ()
    {
        return sirket;
    }

    public void setSirket (String sirket)
    {
        this.sirket = sirket;
    }

    public String getKulid ()
    {
        return kulid;
    }

    public void setKulid (String kulid)
    {
        this.kulid = kulid;
    }

    public String getDeneyimalan ()
    {
        return deneyimalan;
    }

    public void setDeneyimalan (String deneyimalan)
    {
        this.deneyimalan = deneyimalan;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getYil ()
    {
        return yil;
    }

    public void setYil (String yil)
    {
        this.yil = yil;
    }

    @Override
    public String toString() {
        return "DeneyimListeleModel{" +
                "sirket='" + sirket + '\'' +
                ", kulid='" + kulid + '\'' +
                ", deneyimalan='" + deneyimalan + '\'' +
                ", id='" + id + '\'' +
                ", yil='" + yil + '\'' +
                '}';
    }
}
