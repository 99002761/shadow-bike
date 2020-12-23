package com.bikeapp.scheduler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bikeapp.bean.Bike;
import com.bikeapp.bean.BikeLog;
import com.bikeapp.service.BikeService;



class BikeSchedulerTest {
	@InjectMocks
	BikeScheduler bikeScheduler;
	
	@Mock
	BikeService bikeService;
	@Mock
	Bike bike;
	HashMap<String, Bike> bikeMap = new HashMap<String, Bike>();
	Map<String, BikeLog> vehicleMap = new HashMap<String, BikeLog>();
	private MockMvc mockMvc;

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
		 MockitoAnnotations.initMocks(this);
	       mockMvc = MockMvcBuilders.standaloneSetup(bikeScheduler).dispatchOptions(true).build();       
	       bike.setVin("123456");
	       bikeService.setVehicleMap(vehicleMap);
		    bikeMap.put(bike.getVin(),bike); 
	}
		 

	@AfterEach
	void tearDown() throws Exception {
		  System.out.println("After each testcases");
	}

	@Test
	void test() {
		
		when(bikeService.getVehicleMap()).thenReturn( vehicleMap);
		Iterator iterator = bikeMap.entrySet().iterator();
	    while (iterator.hasNext()){
	    @SuppressWarnings("unchecked")
		Entry<String,Bike> entry  = (Entry<String,Bike>) iterator.next();
	    }
	}

		
	}

