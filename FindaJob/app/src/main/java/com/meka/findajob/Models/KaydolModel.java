package com.meka.findajob.Models;

public class KaydolModel{
	private boolean tf;
	private String dogrulamakodu;
	private String text;

	public boolean isTf() {
		return tf;
	}

	public void setTf(boolean tf) {
		this.tf = tf;
	}

	public String getDogrulamakodu() {
		return dogrulamakodu;
	}

	public void setDogrulamakodu(String dogrulamakodu) {
		this.dogrulamakodu = dogrulamakodu;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "KaydolModel{" +
				"tf=" + tf +
				", dogrulamakodu='" + dogrulamakodu + '\'' +
				", text='" + text + '\'' +
				'}';
	}
}
