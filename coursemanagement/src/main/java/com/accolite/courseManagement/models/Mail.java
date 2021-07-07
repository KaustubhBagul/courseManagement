package com.accolite.courseManagement.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;

@Component
public class Mail {

    @Autowired
    private JavaMailSender mailSender;
	
    public void sendSimpleMessage(String to, String subject, String text) {
    	
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("marriagegayatri@gmail.com");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        
        mailSender.send(message);
    }
}