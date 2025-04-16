package com.memmcol.emailmqttclientservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "Audits-Log")
public class MqttMessageEntity implements Serializable {

    @Id
    private String id;

    private String sbcId;

    private String topic;

    private String description;

    private String status;

    private String type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public MqttMessageEntity(String sbcId, String topic, String status, String description, String type) {
        this.sbcId = sbcId;
        this.topic = topic;
        this.status = status;
        this.description = description;
        this.type = type;
        this.createdAt = new Date();
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

    public void setDescription(String status) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
