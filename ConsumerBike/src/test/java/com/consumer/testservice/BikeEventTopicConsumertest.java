package com.consumer.testservice;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	import org.json.simple.JSONObject;
	import org.json.simple.JSONValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
    import org.springframework.kafka.annotation.KafkaListener;
	import org.springframework.stereotype.Component;

	/*@Component
	public class BikeEventTopicConsumertest {

	    private final List<String> messages = new ArrayList<>();


	    private final String url = "jdbc:postgresql://localhost/Bike";
        private final String user = "postgres";
        private final String password = "shravani";

     
	public Connection connect() {
	Connection conn = null;
	try {
	     conn = DriverManager.getConnection(url, user,password);
	    System.out.println("Connected to the PostgreSQL server successfully.");
		 } 
	catch (SQLException e) {
	       System.out.println(e.getMessage());
	   }
	return conn;
	}

	@KafkaListener(topics = "BikeEvent", groupId = "kafka-sandbox")
	@Test
		public void listen(String message) {
			Object file = JSONValue.parse(message);
			JSONObject jsonObjectdecode = (JSONObject)file;
			Long id =(Long)jsonObjectdecode.get("1234");
			String vin =(String)jsonObjectdecode.get("vyy543");
			Double latitude=(Double)jsonObjectdecode.get("22.6");
			Double longitude=(Double)jsonObjectdecode.get("21.7");
			Long altitude =(Long)jsonObjectdecode.get("12");
			Long heading =(Long)jsonObjectdecode.get("50");
			Long speed =(Long)jsonObjectdecode.get("90");
			String brake_operation =(String)jsonObjectdecode.get("on");
			Long tire_pressure_front =(Long)jsonObjectdecode.get("100");
			Long tire_pressure_rear =(Long)jsonObjectdecode.get("50");
			String light_status =(String)jsonObjectdecode.get("off");
			Double handle_angle=(Double)jsonObjectdecode.get("55.5");
			

			BikeEventTopicConsumertest bikeEventTopicConsumer = new BikeEventTopicConsumertest();
			Connection conn = bikeEventTopicConsumer.connect();
			try {
			        Statement stmnt = null;
			        stmnt = connect().createStatement();
			        String query = "INSERT INTO bikeevent(id, vin,latitude,longitude,altitude,heading,speed,brake_operation,tire_pressure_front,tire_pressure_rear,light_status,handle_angle) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			        PreparedStatement pst = conn.prepareStatement(query);       
			        pst.setLong(1,1234);
			        pst.setString(2,"vyy543");
			        pst.setDouble(3,22.6);
			        pst.setDouble(4,21.7);
			        pst.setLong(5,12);
			        pst.setLong(6,50);
			       pst.setLong(7,90);
			        pst.setString(8,"on");
			        pst.setLong(9,100);
			        pst.setLong(10,50);
			        pst.setString(11,"off");
			        pst.setDouble(12,55.5);
			        System.out.println("after insert");
			        pst.executeUpdate();
			        System.out.println("Afterupdate");
			
				} 
			catch (SQLException e) {
					    System.out.println(e.getMessage());
			}
			
			synchronized (messages) {
			        messages.add(message);
			    }
	}




			public List<String> getMessages() {
			
			    return messages;


	}
	}*/
	


