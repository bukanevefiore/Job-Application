package com.meka.findajob.Models;

public class DeneyimSilModel{
	private String text;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"DeneyimSilModel{" + 
			"text = '" + text + '\'' + 
			"}";
		}
}
