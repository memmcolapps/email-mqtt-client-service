//package com.memmcol.emailmqttclientservice.service;
//
//import com.memmcol.emailmqttclientservice.controller.EmailController;
//import com.memmcol.emailmqttclientservice.model.ExceptionErrorLogs;
//import com.memmcol.emailmqttclientservice.model.MqttMessageEntity;
//import com.memmcol.emailmqttclientservice.repository.*;
//import jakarta.annotation.PostConstruct;
//import org.eclipse.paho.client.mqttv3.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//
// @Service
// public class MqttListenerService {
//
//     private static final Logger log = LoggerFactory.getLogger(MqttListenerService.class);
//
////     @Autowired
//     private final MqttClient mqttClient;
//
////     @Autowired
//     private final MqttMessageRepository messageRepository;
//
//     private final ExceptionErrorLogs exceptionErrorLogs = new ExceptionErrorLogs();
//
//     @Autowired private ExceptionAuditRepository exceptionAuditRepository;
//
//     @Autowired
//     private SbcMapper sbcMapper; // Inject MyBatis repository
//
//     public MqttListenerService(MqttClient mqttClient, MqttMessageRepository messageRepository) {
//         this.mqttClient = mqttClient;
//         this.messageRepository = messageRepository;
//     }
//
//     @PostConstruct
//     public void startListening() throws MqttException {
//         try {
//             connectAndSubscribe();
//
//             mqttClient.setCallback(new MqttCallback() {
//                 @Override
//                 public void connectionLost(Throwable cause) {
//                     System.err.println("Connection lost: " + cause.getMessage());
//                     cause.printStackTrace();
//                     handleReconnection();
//                 }
//
//                 @Override
//                 public void messageArrived(String topic, MqttMessage message) throws Exception {
//                     String status = new String(message.getPayload());
//                     System.out.println("Message arrived from topic: " + topic + " with payload: " + status);
//                     String sbcId = topic.split("/")[0];
//                     String topicId = topic.split("/")[1];
//                     String description = "Message arrived from SBC [" + sbcId + "] via breaker [" + topic + "].";
//                     MqttMessageEntity entity = new MqttMessageEntity(sbcId, topicId, status, description, "mqtt");
//                     messageRepository.save(entity);
//                 }
//
//                 @Override
//                 public void deliveryComplete(IMqttDeliveryToken token) {
//                     // Not needed for subscriptions
//                 }
//             });
//
//         } catch (MqttException exception) {
//             System.err.println("Error starting MQTT listener: " + exception.getMessage());
//             exception.printStackTrace();
//             log.error("Error occurred while [ACTION]: {}", exception.getMessage(), exception);
//             exceptionErrorLogs.setDescription("Error occurred while trying to start MQTT listener");
//             exceptionErrorLogs.setError_message(exception.getMessage());
//             exceptionErrorLogs.setError(exception);
//             exceptionAuditRepository.save(exceptionErrorLogs);// Log th
////             throw new RuntimeException("Failed to connect to MQTT broker: " + exception.getMessage(), exception);
//             throw exception;
//         }
//     }
//
//     private void connectAndSubscribe() throws MqttException {
//         if (!mqttClient.isConnected()) {
//             System.out.println("Connecting to broker...");
//             mqttClient.connect();
//             System.out.println("Connected to broker.");
//         }
//
//         // Fetch topics from the database
//         List<SbcRegisterRequest> topics = sbcMapper.getAllSbcWithTopics();
//         if (topics == null || topics.isEmpty()) {
//             System.out.println("No topics found in the database.");
//             return;
//         }
//         for (SbcRegisterRequest topic : topics) {
//             if (topic == null || topic.getSbc() == null || topic.getSbcSubscribeTopic() == null) {
//                 System.err.println("Skipping null topic entry.");
//                 continue;
//             }
//
//             String sbcId = topic.getSbc().getSbcId();
//             SbcSubscribeTopic subscribeTopic = topic.getSbcSubscribeTopic();
//
//             subscribeToTopic(sbcId, subscribeTopic.getSb1Topic());
//             subscribeToTopic(sbcId, subscribeTopic.getSb2Topic());
//             subscribeToTopic(sbcId, subscribeTopic.getSb3Topic());
//             subscribeToTopic(sbcId, subscribeTopic.getSb4Topic());
//             subscribeToTopic(sbcId, subscribeTopic.getSb5Topic());
//             subscribeToTopic(sbcId, subscribeTopic.getSb6Topic());
//         }
//     }
//
//     private void subscribeToTopic(String sbcId, String topic) throws MqttException {
//         if (topic == null || topic.isEmpty()) {
//             System.err.println("Subscription for null or empty topic.");
//             return;
//         }
//         String fullTopic = sbcId + "/" + topic;
//         mqttClient.subscribe(fullTopic);
//         System.out.println("Subscribed to topic: " + fullTopic);
//     }
//
//
//     private void handleReconnection() {
//         new Thread(() -> {
//             while (!mqttClient.isConnected()) {
//                 try {
//                     System.out.println("Attempting to reconnect to MQTT broker...");
//                     mqttClient.connect();
//                     System.out.println("Reconnected to MQTT broker.");
//
//                     // Fetch topics from the database
//                     List<SbcRegisterRequest> topics = sbcMapper.getAllSbcWithTopics();
//                     if (topics == null || topics.isEmpty()) {
//                         System.out.println("Reconnect No topics found in the database.");
//                         return;
//                     }
//                     for (SbcRegisterRequest topic : topics) {
//                         if (topic == null || topic.getSbc() == null || topic.getSbcSubscribeTopic() == null) {
//                             System.err.println("Reconnect-skipping null topic entry.");
//                             continue;
//                         }
//
//                         String sbcId = topic.getSbc().getSbcId();
//                         SbcSubscribeTopic subscribeTopic = topic.getSbcSubscribeTopic();
//
//                         subscribeToTopic(sbcId, subscribeTopic.getSb1Topic());
//                         subscribeToTopic(sbcId, subscribeTopic.getSb2Topic());
//                         subscribeToTopic(sbcId, subscribeTopic.getSb3Topic());
//                         subscribeToTopic(sbcId, subscribeTopic.getSb4Topic());
//                         subscribeToTopic(sbcId, subscribeTopic.getSb5Topic());
//                         subscribeToTopic(sbcId, subscribeTopic.getSb6Topic());
//                     }
// //                    // Re-fetch topics and re-subscribe after reconnecting
// //                    List<SbcRegisterRequest> topics = sbcMapper.getAllSbcWithTopics();
// //                    for (SbcRegisterRequest topic : topics) {
// //                        mqttClient.subscribe(topic.getSbc().getSbcId()+"/"+topic.getSbcSubscribeTopic().getSb1Topic());
// //                        mqttClient.subscribe(topic.getSbc().getSbcId()+"/"+topic.getSbcSubscribeTopic().getSb2Topic());
// //                        mqttClient.subscribe(topic.getSbc().getSbcId()+"/"+topic.getSbcSubscribeTopic().getSb3Topic());
// //                        mqttClient.subscribe(topic.getSbc().getSbcId()+"/"+topic.getSbcSubscribeTopic().getSb4Topic());
// //                        mqttClient.subscribe(topic.getSbc().getSbcId()+"/"+topic.getSbcSubscribeTopic().getSb5Topic());
// //                        mqttClient.subscribe(topic.getSbc().getSbcId()+"/"+topic.getSbcSubscribeTopic().getSb6Topic());
// //                        System.out.println("Re-subscribed to topic: " + topic);
// //                    }
//                 } catch (MqttException exception) {
//                     exception.printStackTrace();
//                     log.error("Error occurred while [ACTION]: {}", exception.getMessage(), exception);
//                     exceptionErrorLogs.setDescription("Error occurred while trying to reconnect to MQTT broker");
//                     exceptionErrorLogs.setError_message(exception.getMessage());
//                     exceptionErrorLogs.setError(exception);
//                     exceptionAuditRepository.save(exceptionErrorLogs);// Log th
//                     try {
//                         Thread.sleep(5000);  // Delay before retrying
//                     } catch (InterruptedException ie) {
//                         ie.printStackTrace();
//                     }
//                 }
//             }
//         }).start();
//     }
// }
//
//
