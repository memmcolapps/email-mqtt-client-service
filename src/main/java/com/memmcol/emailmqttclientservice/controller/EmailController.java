package com.memmcol.emailmqttclientservice.controller;

import com.memmcol.emailmqttclientservice.model.ExceptionErrorLogs;
import com.memmcol.emailmqttclientservice.repository.ExceptionAuditRepository;
import com.memmcol.emailmqttclientservice.service.EmailService;
import com.memmcol.emailmqttclientservice.util.ResponseMap;
import com.memmcol.emailmqttclientservice.util.ResponseProperties;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import javax.mail.MessagingException;
import java.util.Map;

@CrossOrigin(origins = "*") // Allow all origins
@RestController
@RequestMapping("/api")
public class EmailController {

    private static final Logger log = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    private ResponseProperties status;

    private final EmailService emailService;

    @Autowired private ExceptionAuditRepository exceptionAuditRepository;

    private final ExceptionErrorLogs exceptionErrorLogs = new ExceptionErrorLogs();

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public Map<String, Object> sendEmail(@RequestBody Map<String, String> request) throws MessagingException {
        String toAddress = request.get("toAddress");
        String subject = request.get("subject");
        String text = request.get("message");
        System.out.println(toAddress);
        System.out.println(subject);
        System.out.println(text);
        String htmlContent = generateEmailTemplate(request);
        try {
            System.out.println(">>>>>>>>>>>>>> email service reached <<<<<<<<<<<<<<<<<<<");
            emailService.sendEmail(toAddress, subject, text, htmlContent);
            return ResponseMap.response(status.getSuccessCode(), "Email sent successfully!", "");
        } catch (Exception exception){
            log.error("Error occurred while [ACTION]: {}", exception.getMessage(), exception);
            exceptionErrorLogs.setDescription("Error occurred while sending email");
            exceptionErrorLogs.setError_message(exception.getMessage());
            exceptionErrorLogs.setError(exception);
            exceptionAuditRepository.save(exceptionErrorLogs);
            throw exception;
        }

    }

    private String generateEmailTemplate(Map<String, String> data) {
        return "<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "<title>OTP Verification</title>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #ffffff; padding: 20px; }" +
                ".container { max-width: 500px; background: #f4f4f4; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2); margin: auto; }" +
                ".header { font-size: 20px; font-weight: bold; color: #333; text-align: center; }" +
                ".content { font-size: 16px; color: #555; line-height: 1.5; text-align: center; }" +
                ".otp-code { font-size: 24px; font-weight: bold; color: #007bff; margin: 20px 0; text-align: center}" +
                ".footer { font-size: 14px; color: #777; text-align: center; margin-top: 20px; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<p class='header'>Your One-Time Password (OTP)</p>" +
                "<p class='content'>Dear Valued Customer,</p>" +
                "<p class='content'>Please use the following OTP code to verify your account:</p>" +
                "<p class='otp-code'>" + data.get("message") + "</p>" +
                "<p class='content'>This code is valid for a limited time. Do not share this code with anyone.</p>" +
                "<p class='footer'>If you did not request this code, please ignore this email.</p>" +
                "<p class='footer'>Best regards,<br> Memmcol Plc.</p>" +
                "</div>" +
                "</body>" +
                "</html>";
    }

}

