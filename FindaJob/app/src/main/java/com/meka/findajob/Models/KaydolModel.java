package com.meka.findajob.Models;

public class KaydolModel {
	private boolean tf;
	private Object text;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setText(Object text){
		this.text = text;
	}

	public Object getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"Kaydol{" + 
			"tf = '" + tf + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}
