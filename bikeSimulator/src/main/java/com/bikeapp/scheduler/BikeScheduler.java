package com.bikeapp.scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bikeapp.service.BikeService;
@EnableScheduling
@Component
public class BikeScheduler {
	Logger logger = LoggerFactory.getLogger(BikeScheduler.class);

@Autowired
BikeService bikeservice;
	
	 @Scheduled(initialDelayString="${TIMEOUT_SCHEDULER_INTIAL_DELAY}",
				fixedRateString="${TIMEOUT_SCHEDULER_DELAY}")
			public void pushBike() throws IOException 
	         {	
			       bikeservice.post();
			 }  

	
	
	
	
}
