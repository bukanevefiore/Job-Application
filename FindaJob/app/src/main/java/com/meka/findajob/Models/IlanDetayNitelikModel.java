package com.meka.findajob.Models;

public class IlanDetayNitelikModel {

    private String ilanid;

    private String id;

    private String nitelik;

    public String getIlanid() {
        return ilanid;
    }

    public void setIlanid(String ilanid) {
        this.ilanid = ilanid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNitelik() {
        return nitelik;
    }

    public void setNitelik(String nitelik) {
        this.nitelik = nitelik;
    }

    @Override
    public String toString() {
        return "IlanDetayNitelikModel{" +
                "ilanid='" + ilanid + '\'' +
                ", id='" + id + '\'' +
                ", nitelik='" + nitelik + '\'' +
                '}';
    }
}
