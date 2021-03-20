package com.meka.findajob.Models;

public class GirisYapModel{
	private boolean tf;
	private String mailadres;
	private String sifre;
	private String kadi;
	private String id;
	private String text;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setMailadres(String mailadres){
		this.mailadres = mailadres;
	}

	public String getMailadres(){
		return mailadres;
	}

	public void setSifre(String sifre){
		this.sifre = sifre;
	}

	public String getSifre(){
		return sifre;
	}

	public void setKadi(String kadi){
		this.kadi = kadi;
	}

	public String getKadi(){
		return kadi;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"GirisYapModel{" + 
			"tf = '" + tf + '\'' + 
			",mailadres = '" + mailadres + '\'' + 
			",sifre = '" + sifre + '\'' + 
			",kadi = '" + kadi + '\'' + 
			",id = '" + id + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}
