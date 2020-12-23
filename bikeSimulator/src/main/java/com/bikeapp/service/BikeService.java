package com.bikeapp.service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bikeapp.bean.Bike;
import com.bikeapp.bean.BikeEvent;
import com.bikeapp.bean.BikeLog;
import com.bikeapp.bean.Location;


@Service
public class BikeService {
	Map<String, Bike> bikeMap = new HashMap<String, Bike>();
	Map<Integer,BikeEvent> bikeEventMap = new HashMap<Integer, BikeEvent>();
	Map<String, BikeLog> vehicleMap = new HashMap<String, BikeLog>();
	

	public Map<String, BikeLog> getVehicleMap() {
		// TODO Auto-generated method stub
		return vehicleMap;
	}
	
	public void setVehicleMap(Map<String, BikeLog> vehicleMap) {
		this.vehicleMap = vehicleMap;
	}

	public BikeService() {
	super();
	// TODO Auto-generated constructor stub
	}

	@Autowired
	private KafkaTemplate<String, Bike> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, BikeEvent> kafkaTemplateBikeEvent;
	
	@Autowired
	private KafkaTemplate<String, BikeLog> kafkaTemplateBikeLog;
	 
	 @Autowired
	 BikeUtil bikeUtil;
	 @Autowired
	 BikeEvent bikeEvent;
	 @Autowired
	 Bike bike;
	 
	
	 String vin;
	 String name;
	 

	private static final String TOPICBIKE = "Bike";
	private static final String TOPICBIKEEVENT = "BikeEvent";
	private static final String TOPICBIKELOG = "BikeLog";
	public String post() throws IOException
	{
		Bike bike = new Bike();
		BikeLog vehicle=vehicleMap.get(vin);
        if(vehicle!=null) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    	Date now = new Date();
    	String strDate = sdf.format(now);
    	 bike.setTimestamp(strDate);
    	 Location location = new Location();
		 bikeUtil.readDataFromExcel(location);
		 bike.setLatitude(location.getLatitude());
		 bike.setLongitude(location.getLongitude());
		 bike.setVin(vehicle.getVin());
		 bike.setOdometer(bikeUtil.getRandomNumberLong(200,300));
		 bike.setEngine_status("ON");
		 bike.setName(vehicle.getName());
		 System.out.println(bike.getLatitude());
		 System.out.println(bike.getLongitude());
		 bike.setEngine_rpm(bikeUtil.getRandomNumber(200,300));
		 Integer rpm = bike.getEngine_rpm();
		 bike.setSpeed(bikeUtil.calculateSpeedFromRpm(rpm));
		 bike.setFuel_capacity(bikeUtil.getRandomNumber(0,15));
		 bike.setEngine_load(bikeUtil.getRandomNumberLong(20,80));
		 bike.setFuel_consumption(bikeUtil.getRandomNumberLong(0,15));
		 bike.setEngine_temp(bikeUtil.getRandomNumberLong(69,100));
		 kafkaTemplate.send(TOPICBIKE, bike);}
        else
        {
        	System.out.println("no vehicle data");
        }
		
		return "successfully posted";
	}
	
	public String bikeEvent()
	{
		bikeEventRandom();
		kafkaTemplateBikeEvent.send(TOPICBIKEEVENT, bikeEvent);
		return "success";
	}
	
	
	public void bikeRegister(BikeLog vehicle)
	{
		 vin = vehicle.getVin();
         name = vehicle.getName();
        vehicleMap.put(vehicle.getVin(), vehicle);
        System.out.println(vehicleMap);
         kafkaTemplateBikeLog.send(TOPICBIKELOG,vehicle);
	}
	
	public String stopSimulation()
	{
		System.out.println(vin);
        vehicleMap.remove(vin);
        return "bike simulation stopped ";
	}
	
	
	public void bikeEventRandom()
	{
		//BikeEvent bikeEvent = new BikeEvent();
		BikeLog vehicle=vehicleMap.get(vin);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    Date now = new Date();
	    String strDate = sdf.format(now);
	    bikeEvent.setTimestamp(strDate);
		bikeEvent.setId(bikeUtil.getEventId());
		bikeEvent.setVin(vehicle.getVin());
		bikeEvent.setLatitude(bikeUtil.getRandomNumberDouble(10,100));
		bikeEvent.setLongitude(bikeUtil.getRandomNumberDouble(10,100));
		bikeEvent.setAltitude(bikeUtil.getRandomNumber(12,50));
		bikeEvent.setHeading(bikeUtil.getRandomNumber(0,360));
		bikeEvent.setSpeed(bikeUtil.getRandomNumber(0,120));
		bikeEvent.setBrake_operation(bikeUtil.getRandomElement());
		bikeEvent.setLight_status(bikeUtil.getRandomElement());
		bikeEvent.setTire_pressure_front(bikeUtil.getRandomNumber(0,35));
		bikeEvent.setTire_pressure_rear(bikeUtil.getRandomNumber(0,40));
		bikeEvent.setHandle_angle(bikeUtil.getRandomNumberDouble(0,180));
}
}