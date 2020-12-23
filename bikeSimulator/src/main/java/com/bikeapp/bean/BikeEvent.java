package com.bikeapp.bean;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BikeEvent {
	Integer id;
	String vin;
	Double latitude;
	Double longitude;
	Integer altitude; 
	Integer heading;
	Integer speed; 
	String brake_operation;
	String light_status; 
	Integer tire_pressure_front;
	Integer tire_pressure_rear;
	Double handle_angle; 
	String timestamp;
	
}
