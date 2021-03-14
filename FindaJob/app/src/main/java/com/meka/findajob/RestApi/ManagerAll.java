package com.meka.findajob.RestApi;



import com.meka.findajob.Models.DogrulaModel;
import com.meka.findajob.Models.KaydolModel;

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



}
