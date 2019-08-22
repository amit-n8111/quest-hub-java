package com.citi.quest.api.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.citi.quest.api.dtos.EmailDTO;

@Component
public class EmailNotification {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	public void sendEmail(EmailDTO emailDTO) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailDTO.getMailReciever());
        //msg.setTo("amitnavarkar111@gmail.com");
        msg.setSubject(emailDTO.getSubject());
       // msg.setSubject("Testing from Spring Boot");
        msg.setText(emailDTO.getMailbody());
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }

}
