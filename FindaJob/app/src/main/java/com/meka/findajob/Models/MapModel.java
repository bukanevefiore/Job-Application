package com.meka.findajob.Models;

public class MapModel {
	private String lot;
	private String title;
	private String lat;

	public void setLot(String lot){
		this.lot = lot;
	}

	public String getLot(){
		return lot;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"MapModel{" + 
			"lot = '" + lot + '\'' + 
			",title = '" + title + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}
