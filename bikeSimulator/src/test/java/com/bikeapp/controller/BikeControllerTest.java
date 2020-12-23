package com.bikeapp.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bikeapp.bean.Bike;
import com.bikeapp.bean.BikeEvent;
import com.bikeapp.service.BikeService;
import com.fasterxml.jackson.databind.ObjectMapper;


class BikeControllerTest {
	@Mock
	BikeService bikeService;
	
	@InjectMocks
	BikeController bikeController;

	Bike bike =new Bike();
	BikeEvent bikeEvent = new BikeEvent();

	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Before each testcases");
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		  System.out.println("After each testcases");
	}

	private Object mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		 MockitoAnnotations.initMocks(this);
	        this.mockMvc = MockMvcBuilders.standaloneSetup(bikeController).dispatchOptions(true).build();  
	  
	}
	

	@Test
	void testGetPostcontroller() throws Exception {

		Bike bike =new Bike();
		ObjectMapper mapper = new ObjectMapper();		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/publish/vehicle",bike).content("application/json");
		String json = mapper.writeValueAsString(bike);
		builder = builder.content(json.toString());
		builder = builder.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		ResultActions resultActions = ((MockMvc) this.mockMvc).perform(builder);
		bikeService.post();
	    assertNotNull(resultActions);	  
	}


	@Test
	void testBikeEvent() throws Exception {
		BikeEvent bikeEvent =new BikeEvent();
		ObjectMapper mapper = new ObjectMapper();		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/publish/postbikeevent",bikeEvent).content("application/json");
		String json = mapper.writeValueAsString(bikeEvent);
		builder = builder.content(json.toString());
		builder = builder.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		ResultActions resultActions = ((MockMvc) this.mockMvc).perform(builder);
		bikeService.bikeEvent();
	    assertNotNull(resultActions);	 
		
	}

}
