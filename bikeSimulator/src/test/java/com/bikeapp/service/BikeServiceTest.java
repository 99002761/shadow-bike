package com.bikeapp.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bikeapp.bean.Bike;
import com.bikeapp.bean.BikeEvent;

class BikeServiceTest {
	Map<String, Bike> bikeMap = new HashMap<String, Bike>();
	Map<Integer,BikeEvent> bikeEventMap = new HashMap<Integer, BikeEvent>();
	@Autowired
	private KafkaTemplate<String, Bike> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, BikeEvent> kafkaTemplateBikeEvent;
	 
	 @Autowired
	 BikeUtil bikeUtil;
	 @Autowired
	 Bike bike;
	 @Autowired
	 BikeEvent bikeEvent;
	private Object mockMvc;
		private static final String TOPICBIKE = "Bike";
		private static final String TOPICBIKEEVENT = "BikeEvent";

		public Map<String, Bike> getBikeMap() {
			return bikeMap;
		}

		/*public void setBikeMap(Map<String, Bike> bikeMap) {
			this.bikeMap = bikeMap;
		}*/

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		  System.out.println("Before each testcases");
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		  System.out.println("After each testcases");
	}

	@BeforeEach
	void setUp() throws Exception {
		  System.out.println("Before each testcases");
	}

	@AfterEach
	void tearDown() throws Exception {
		  System.out.println("After each testcases");
	}

/*	@Test
	void testBikeService() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBikeMap() {
		fail("Not yet implemented");
	}

	@Test
	void testSetBikeMap() {
		fail("Not yet implemented");
	}

	@Test
	void testPostBikeEvent() {
		fail("Not yet implemented");
	}

	@Test
	void testPost() {
		fail("Not yet implemented");
	}

	@Test
	void testBikeEvent() {
		fail("Not yet implemented");
	}
*/
	@Test
	void testBikeRandom() {
		 MockitoAnnotations.initMocks(this);
	      // this.mockMvc = MockMvcBuilders.standaloneSetup(BikeEvent).dispatchOptions(true).build();       
	    //vehicleEvent.setVin("123456");
	    bike.setVin("123456");
	    bike.setLatitude(12.3565);
	    bike.setLongitude(12.3565);
	    bikeMap.put( bike.getVin(),bike);
	  //  BikeService.setBikeMap(bikeMap);
	    
	        /*bike.setVin("honda2010a7");
	        bike.setOdometer(bikeUtil.getRandomNumberLong(200,300));
	        long odometerValue =bike.getOdometer();
	        bike.setSpeed(bikeUtil.calculateSpeedFromRpm(odometerValue));
	        bike.setEngine_status("off");
	        //bike.setLatitude(bikeUtil.getRandomNumberDouble(50,100));
	        //bike.setLongitude(bikeUtil.getRandomNumberDouble(25,30));
	        bike.setName("honda");
	        System.out.println(bike.getLatitude());
	        System.out.println(bike.getLongitude());
	        bike.setEngine_rpm(bikeUtil.getRandomNumber(200,300));
	        bike.setFuel_capacity(bikeUtil.getRandomNumber(0,15));
	        bike.setEngine_load(bikeUtil.getRandomNumberLong(20,80));
	        bike.setFuel_consumption(bikeUtil.getRandomNumberLong(0,15));
	        bike.setEngine_temp(bikeUtil.getRandomNumberLong(69,100));
	   
		
		fail("Not yet implemented");*/
	}

/*	@Test
	void testBikeEventRandom() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        bikeEvent.setLatitude(bike.getLatitude());
        System.out.println(bikeEvent.getLatitude());
        bikeEvent.setLongitude(bike.getLongitude());
        System.out.println(bikeEvent.getLongitude());
        bikeEvent.setTimestamp(strDate);
        bikeEvent.setId(bikeUtil.getEventId()+1);
        bikeEvent.setVin("honda2010a7");
        //bikeEvent.setLatitude(bikeUtil.getRandomNumberDouble(10,100));
        //bikeEvent.setLongitude(bikeUtil.getRandomNumberDouble(10,100));
        bikeEvent.setAltitude(bikeUtil.getRandomNumber(12,50));
        bikeEvent.setHeading(bikeUtil.getRandomNumber(0,360));
        bikeEvent.setSpeed(bikeUtil.getRandomNumber(0,120));
        bikeEvent.setBrake_operation(bikeUtil.getRandomElement());
        bikeEvent.setLight_status(bikeUtil.getRandomElement());
        bikeEvent.setTire_pressure_front(bikeUtil.getRandomNumber(0,35));
        bikeEvent.setTire_pressure_rear(bikeUtil.getRandomNumber(0,40));
        bikeEvent.setHandle_angle(bikeUtil.getRandomNumberDouble(0,180));
		fail("Not yet implemented");
	}*/

}
