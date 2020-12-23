package com.consumer.services;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

   
 @Component
 public class TopicConsumer {
                private final List<String> messages = new ArrayList<>();
                private final String url = "jdbc:postgresql://localhost/Bike";
                private final String user = "postgres";
                private final String password = "shravani";

 

        public Connection connect() {
        Connection conn = null;
         try {
                 conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the PostgreSQL server successfully.");

             } catch (SQLException e) {
                   System.out.println(e.getMessage());
                     }

 

         return conn;
        }

        @KafkaListener(topics = "Bike", groupId = "kafka-sandbox")
        public void listen(String message) {
           Object file = JSONValue.parse(message);
           JSONObject jsonObjectdecode = (JSONObject)file;
           String vin =(String)jsonObjectdecode.get("vin");
           String engine_status=(String)jsonObjectdecode.get("engine_status");
           Double latitude=(Double)jsonObjectdecode.get("latitude");
           String name = (String)jsonObjectdecode.get("name");
           Long odometer=(Long)jsonObjectdecode.get("odometer");
           Long engine_rpm=(Long)jsonObjectdecode.get("engine_rpm");
           Long speed=(Long)jsonObjectdecode.get("speed");
           Long fuel_capacity=(Long)jsonObjectdecode.get("fuel_capacity");
           Long engine_load=(Long)jsonObjectdecode.get("engine_load");
           Double longitude=(Double)jsonObjectdecode.get("longitude");
           Long fuel_consumption=(Long)jsonObjectdecode.get("fuel_consumption");
           Long engine_temp=(Long)jsonObjectdecode.get("engine_temp");
           String timestamp=(String)jsonObjectdecode.get("timestamp");

    

               TopicConsumer topicConsumer = new TopicConsumer();
               Connection conn = topicConsumer.connect();
               try {
                       Statement stmnt = null;
                       stmnt = connect().createStatement();
                       String query = "INSERT INTO bike(vin, engine_status, latitude,longitude,name,engine_rpm,speed,fuel_capacity,fuel_consumption,odometer,engine_load,engine_temp,timestamp) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                       PreparedStatement pst = conn.prepareStatement(query);       
                       pst.setString(1,vin);
                       pst.setString(2,engine_status);
                       pst.setDouble(3,latitude);
                       pst.setDouble(4,longitude);
                       pst.setString(5, name);
                       pst.setLong(6,engine_rpm);
                       pst.setLong(7,speed);
                       pst.setLong(8,fuel_capacity); 
                       pst.setLong(9,fuel_consumption);
                       pst.setLong(10,odometer);
                       pst.setLong(11,engine_load);
                       pst.setLong(12,engine_temp);
                       pst.setString(13,timestamp);
                       
                       
                       System.out.println("after insert");
                       pst.executeUpdate();
                       System.out.println("Afterupdate");
    
               } catch (SQLException e) {
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
 