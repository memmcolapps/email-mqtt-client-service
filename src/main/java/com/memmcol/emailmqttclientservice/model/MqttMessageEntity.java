package com.memmcol.emailmqttclientservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Audits-Log")
public class MqttMessageEntity {

    @Id
    private String id;
    private String sbcId;
    private String topic;
    private String description;
    private String type;
    private LocalDateTime createdAt;

    public MqttMessageEntity(String sbcId, String topic, String description, String type, LocalDateTime receivedAt) {
        this.sbcId = sbcId;
        this.topic = topic;
        this.description = description;
        this.type = type;
        this.createdAt = receivedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSbcId() {
        return sbcId;
    }

    public void setSbcId(String sbcId) {
        this.sbcId = sbcId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
