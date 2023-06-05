package com.ecommerce.mailingservice;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/email")
@CrossOrigin("*")
@AllArgsConstructor
public class MailController {

        private final EmailService emailService;

       @PostMapping("/sendMail")
        public String sendMail(){
            System.out.println("Spring Mail - Sending Simple Email with JavaMailSender Example");
            Mail mail = new Mail();
            mail.setFrom("******@gmail.com");
            mail.setTo("asma@gmail.com");
            mail.setSubject("Sending Simple Email with JavaMailSender Example");
            mail.setContent("This tutorial demonstrates how to send a simple email using Spring Framework.");
            emailService.sendSimpleMessage(mail);
            return "ok";
        }

}

