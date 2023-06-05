package com.ecommerce.mailingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MailingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailingApplication.class, args);
    }
}
