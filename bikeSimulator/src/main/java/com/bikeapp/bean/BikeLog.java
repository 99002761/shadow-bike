package com.bikeapp.bean;

import org.springframework.stereotype.Component;

@Component
public class BikeLog {

	private String vin;
	private String name;
	
	
	
	
	public String getVin() {
		return vin;
	}
	public void setVin(String string) {
		this.vin = string;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
