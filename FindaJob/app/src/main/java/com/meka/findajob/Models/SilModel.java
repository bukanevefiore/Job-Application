package com.meka.findajob.Models;

public class SilModel {
	private String text;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
	public String toString() {
		return "SilModel{" +
				"text='" + text + '\'' +
				'}';
	}
}
