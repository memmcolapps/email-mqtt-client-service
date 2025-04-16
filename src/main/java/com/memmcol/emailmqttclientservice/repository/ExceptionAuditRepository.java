package com.memmcol.emailmqttclientservice.repository;

import com.memmcol.emailmqttclientservice.model.ExceptionErrorLogs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExceptionAuditRepository extends MongoRepository<ExceptionErrorLogs, String> {
}
