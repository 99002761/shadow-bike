package com.consumer.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class BikeEventTopicConsumer {
   private final List<String> messages = new ArrayList<>();
   private final String url = "jdbc:postgresql://localhost/Bike";
   private final String user = "postgres";
   private final String password = "shravani";



   public Connection connect() {
   Connection conn = null;
   try {
        conn = DriverManager.getConnection(url, user, password);
       System.out.println("Connected to the PostgreSQL server successfully.");
        } 
   catch (SQLException e) {
          System.out.println(e.getMessage());
      }
   return conn;
   }

@KafkaListener(topics = "BikeEvent", groupId = "kafka-sandbox")
   public void listen(String message) {
       Object file = JSONValue.parse(message);
       JSONObject jsonObjectdecode = (JSONObject)file;
       Long id =(Long)jsonObjectdecode.get("id");
       String vin =(String)jsonObjectdecode.get("vin");
       Double latitude=(Double)jsonObjectdecode.get("latitude");
       Double longitude=(Double)jsonObjectdecode.get("longitude");
       Long altitude =(Long)jsonObjectdecode.get("altitude");
       Long heading =(Long)jsonObjectdecode.get("heading");
       Long speed =(Long)jsonObjectdecode.get("speed");
       String brake_operation =(String)jsonObjectdecode.get("brake_operation");
       String light_status =(String)jsonObjectdecode.get("light_status");
       Long tire_pressure_front =(Long)jsonObjectdecode.get("tire_pressure_front");
       Long tire_pressure_rear =(Long)jsonObjectdecode.get("tire_pressure_rear");
       Double handle_angle=(Double)jsonObjectdecode.get("handle_angle");
       String timestamp = (String)jsonObjectdecode.get("timestamp");
       String name = (String)jsonObjectdecode.get("name");
 
       BikeEventTopicConsumer bikeEventTopicConsumer = new BikeEventTopicConsumer();
       Connection conn = bikeEventTopicConsumer.connect();
       try {
               Statement stmnt = null;
               stmnt = connect().createStatement();
               String query = "INSERT INTO bikeevent(id,vin,latitude,longitude,altitude,heading,speed,brake_operation,light_status,tire_pressure_front,tire_pressure_rear,handle_angle,timestamp,name)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
               PreparedStatement pst = conn.prepareStatement(query);       
               pst.setLong(1,id);
               pst.setString(2,vin);
               pst.setDouble(3,latitude);
               pst.setDouble(4,longitude);
               pst.setLong(5,altitude);
               pst.setLong(6,heading);
               pst.setLong(7,speed);
               pst.setString(8,brake_operation);
               pst.setString(9,light_status);
               pst.setLong(10,tire_pressure_front);
               pst.setLong(11,tire_pressure_rear);
               pst.setDouble(12,handle_angle);
               pst.setString(13,timestamp);
               pst.setString(14,name);
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
}