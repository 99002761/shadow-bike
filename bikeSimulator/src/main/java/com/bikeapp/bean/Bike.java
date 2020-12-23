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
public class Bike {
	private String vin;
    private Integer speed;
    private String engine_status;
    private Double latitude;
    private Double longitude;
    private String name;
    private Long odometer;
    private Integer engine_rpm;
    private Integer fuel_capacity;
    private Long engine_load;
    private Long fuel_consumption;
    private Long engine_temp;
    private String timestamp;
}