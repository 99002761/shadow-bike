
package com.bikeapp.controller;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeapp.bean.Bike;
import com.bikeapp.bean.BikeEvent;
import com.bikeapp.bean.BikeLog;
import com.bikeapp.scheduler.BikeScheduler;
import com.bikeapp.service.BikeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("kafka")
public class BikeController {
	

	@Autowired
	BikeService bikeservice;
	
	@Autowired
	private ScheduledAnnotationBeanPostProcessor postProcessor;

	@Autowired
	private BikeScheduler bikeScheduler;
	
	@Autowired
	private BikeLog bikeLog;

	@Autowired
	private ObjectMapper objectMapper;
	
	private static final String SCHEDULED_TASKS = "scheduledTasks";


	@GetMapping("/bike/get")
	public String getController()
	{
	return "get";
	}

	@Autowired
	private KafkaTemplate<String, Bike> kafkaTemplate;
	
	private static final String TOPICBIKE = "Bike";
	
	@CrossOrigin
    @PostMapping("/publish/vehicle")
    public String postController(@RequestBody String vehicle)
    {
        Object file = JSONValue.parse(vehicle);
        JSONObject jsonObjectdecode = (JSONObject)file;
        String vid=(String)jsonObjectdecode.get("vin");
        
        System.out.println(vid);
        
        if(vid.isEmpty()) {
            return "please enter details";
        }
        else {
            String vin=(String)jsonObjectdecode.get("vin");
            String name=(String)jsonObjectdecode.get("name");
           
            bikeLog.setVin(vin);
            bikeLog.setName(name);
            bikeservice.bikeRegister(bikeLog);
            System.out.println(bike);
       
            return"sucessfully posted";
       
          }
        
    }
	
	
   @GetMapping("/publish")
   public String post() throws IOException
   { 
		 bikeservice.post();
		 return"Published successfully";
    }
	
	
   @GetMapping("/publish/postbikeevent")
   public String bikeEvent() throws IOException
   { 
		bikeservice.bikeEvent();
		return"Published event data successfully";
   }
	
    @GetMapping(value = "/stopScheduler")
    public String stopSchedule(){
	  bikeservice.stopSimulation();
	  return"Simulation stopped successfully";
    }

	
}
	
	
	
	
	