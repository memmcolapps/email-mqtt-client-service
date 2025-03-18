// package com.memmcol.emailmqttclientservice.config;

// import org.eclipse.paho.client.mqttv3.MqttClient;
// import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
// import org.eclipse.paho.client.mqttv3.MqttException;
// import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class MQTTConfig {

//     private static final String BROKER_URL = "tcp://iot.memmserve.com:1883"; //tcp://broker.hivemq.com:1883";
//     private static final String CLIENT_ID = "mqtt-client-" + System.currentTimeMillis();

//     @Bean
//     public MqttClient mqttClient() {
//         try {
//             MqttClient client = new MqttClient(BROKER_URL, CLIENT_ID, new MemoryPersistence());
//             MqttConnectOptions options = new MqttConnectOptions();
//             options.setAutomaticReconnect(true);
//             options.setCleanSession(true);
//             options.setConnectionTimeout(10);

//             client.connect(options);
//             return client;
//         } catch (MqttException e) {
//             e.printStackTrace();  // Log the error
//             throw new RuntimeException("Failed to connect to MQTT broker: " + e.getMessage(), e);
//         }
//     }
// }

// //@Configuration
// //public class MQTTConfig {
// //
// //    private static final String BROKER_URL = "tcp://broker.hivemq.com:1883";//"tcp://iot.memmserve.com:1883";
// //    private static final String CLIENT_ID = "mqtt-client-" + System.currentTimeMillis();
// //
// //    @Bean
// //    public MqttClient mqttClient() throws MqttException {
// //        MqttClient client = new MqttClient(BROKER_URL, CLIENT_ID, new MemoryPersistence());
// //        MqttConnectOptions options = new MqttConnectOptions();
// //        options.setAutomaticReconnect(true);
// //        options.setCleanSession(true);
// //        options.setConnectionTimeout(10);
// //
// //        client.connect(options);
// //        return client;
// //    }
// //}

