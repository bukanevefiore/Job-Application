package com.meka.findajob.Models;

public class YetenekListeleModel {

    private String kulid;

    private String yetenek;

    private String id;

    public String getKulid() {
        return kulid;
    }

    public void setKulid(String kulid) {
        this.kulid = kulid;
    }

    public String getYetenek() {
        return yetenek;
    }

    public void setYetenek(String yetenek) {
        this.yetenek = yetenek;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "YetenekListeleModel{" +
                "kulid='" + kulid + '\'' +
                ", yetenek='" + yetenek + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
