package com.memmcol.emailmqttclientservice;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class EmailMqttClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailMqttClientServiceApplication.class, args);
    }
}
