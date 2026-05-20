package com.memmcol.emailmqttclientservice.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SbcPublishTopic implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;

	private String sbcId;

	private String sbcTopic;

	private String sb1Topic;

	private String sb2Topic;

	private String sb3Topic;

	private String sb4Topic;

	private String sb5Topic;

	private String sb6Topic;

	private Long hierarchyId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    public SbcPublishTopic() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getSbcId() {
        return sbcId;
    }

    public void setSbcId(String sbcId) {
        this.sbcId = sbcId;
    }

    public String getSbcTopic() {
        return sbcTopic;
    }

    public void setSbcTopic(String sbcTopic) {
        this.sbcTopic = sbcTopic;
    }

    public String getSb1Topic() {
        return sb1Topic;
    }

    public void setSb1Topic(String sb1Topic) {
        this.sb1Topic = sb1Topic;
    }

    public String getSb2Topic() {
        return sb2Topic;
    }

    public void setSb2Topic(String sb2Topic) {
        this.sb2Topic = sb2Topic;
    }

    public String getSb3Topic() {
        return sb3Topic;
    }

    public void setSb3Topic(String sb3Topic) {
        this.sb3Topic = sb3Topic;
    }

    public String getSb4Topic() {
        return sb4Topic;
    }

    public void setSb4Topic(String sb4Topic) {
        this.sb4Topic = sb4Topic;
    }

    public String getSb5Topic() {
        return sb5Topic;
    }

    public void setSb5Topic(String sb5Topic) {
        this.sb5Topic = sb5Topic;
    }

    public String getSb6Topic() {
        return sb6Topic;
    }

    public void setSb6Topic(String sb6Topic) {
        this.sb6Topic = sb6Topic;
    }

    public Long getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(Long hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
