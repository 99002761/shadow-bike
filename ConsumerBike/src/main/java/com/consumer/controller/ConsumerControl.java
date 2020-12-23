package com.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumer.services.BikeEventTopicConsumer;
import com.consumer.services.TopicConsumer;



@RestController
public class ConsumerControl {
	

	
	@Autowired
	TopicConsumer topicConsumer;
	@Autowired
	BikeEventTopicConsumer bikeeventConsumer;
		 
    @GetMapping("/messages")
    public List<String> getmessages() {
    	
    	return topicConsumer.getMessages();
    }



    @GetMapping("/messages/bikeevent")
    public List<String> getmessages1() {
    	
    	return bikeeventConsumer.getMessages();
    }
	
}
