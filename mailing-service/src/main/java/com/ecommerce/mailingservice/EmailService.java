package com.ecommerce.mailingservice;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
        private final JavaMailSender emailSender ;

        public void sendSimpleMessage(final Mail mail){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(mail.getSubject());
            message.setText(mail.getContent());
            message.setTo(mail.getTo());
            message.setFrom(mail.getFrom());

            emailSender.send(message);
        }

    }

