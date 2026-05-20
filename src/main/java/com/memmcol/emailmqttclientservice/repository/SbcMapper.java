package com.memmcol.emailmqttclientservice.repository;//package com.memmcol.smartemqttclient.repository;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SbcMapper {
    @Results({
            @Result(property = "sbc", column = "SbcId", one = @One(select = "com.memmcol.emailmqttclientservice.repository.SbcMapper.getAllSbc")),
            @Result(property = "sbcSubscribeTopic", column = "SbcId", one = @One(select = "com.memmcol.emailmqttclientservice.repository.SbcMapper.getAllSubscribeTopics")),
            @Result(property = "sbcPublishTopic", column = "SbcId", one = @One(select = "com.memmcol.emailmqttclientservice.repository.SbcMapper.getAllPublishTopics")) })
    @Select("SELECT s.*, st.*, pt.*  FROM Sbcs s " + "JOIN Sbc_Subscribe_Topic st ON s.SbcId = st.SbcId "
            + "JOIN Sbc_Publish_Topic pt ON s.SbcId = pt.SbcId ")
    List<SbcRegisterRequest> getAllSbcWithTopics();

    @Select("SELECT * FROM Sbcs WHERE SbcId = #{sbcId}")
    List<Sbc> getAllSbc(String sbcId);

    @Select("SELECT * FROM Sbc_Subscribe_Topic WHERE SbcId IN (SELECT SbcId FROM Sbcs WHERE SbcId = #{id})")
    List<SbcSubscribeTopic> getAllSubscribeTopics(String id);

    @Select("SELECT * FROM Sbc_Publish_Topic WHERE SbcId IN (SELECT SbcId FROM Sbcs WHERE SbcId = #{id})")
    List<SbcPublishTopic> getAllPublishTopics(String id);

}
