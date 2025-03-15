package com.memmcol.emailmqttclientservice.repository;


import com.memmcol.emailmqttclientservice.model.MqttMessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MqttMessageRepository extends MongoRepository<MqttMessageEntity, String> {
}
