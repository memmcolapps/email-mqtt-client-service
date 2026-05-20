// package com.memmcol.emailmqttclientservice.config;
//
// import com.memmcol.emailmqttclientservice.controller.EmailController;
// import com.memmcol.emailmqttclientservice.model.ExceptionErrorLogs;
// import com.memmcol.emailmqttclientservice.repository.ExceptionAuditRepository;
// import org.eclipse.paho.client.mqttv3.MqttClient;
// import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
// import org.eclipse.paho.client.mqttv3.MqttException;
// import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// @Configuration
// public class MQTTConfig {
//     private static final Logger log = LoggerFactory.getLogger(MQTTConfig.class);
//
//     private static final String BROKER_URL = "tcp://172.16.10.29:1883"; //tcp://broker.hivemq.com:1883"; "tcp://iot.memmserve.com:1883"
//
//     private static final String CLIENT_ID = "mqtt-client-" + System.currentTimeMillis();
//
//     @Autowired private ExceptionAuditRepository exceptionAuditRepository;
//
//     private final ExceptionErrorLogs exceptionErrorLogs = new ExceptionErrorLogs();
//
//     @Bean
//     public MqttClient mqttClient() {
//         try {
//             MqttClient client = new MqttClient(BROKER_URL, CLIENT_ID, new MemoryPersistence());
//             MqttConnectOptions options = new MqttConnectOptions();
//             options.setAutomaticReconnect(true);
//             options.setCleanSession(true);
//             options.setConnectionTimeout(10);
//
//             client.connect(options);
//             return client;
//         } catch (MqttException exception) {
//             exception.printStackTrace();
//             log.error("Error occurred while [ACTION]: {}", exception.getMessage(), exception);
//             exceptionErrorLogs.setDescription("Error occurred while trying to connect to MQTT broker");
//             exceptionErrorLogs.setError_message(exception.getMessage());
//             exceptionErrorLogs.setError(exception);
//             exceptionAuditRepository.save(exceptionErrorLogs);// Log the error
//             throw new RuntimeException("Failed to connect to MQTT broker: " + exception.getMessage(), exception);
//         }
//     }
// }
//
// //@Configuratio
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
//
