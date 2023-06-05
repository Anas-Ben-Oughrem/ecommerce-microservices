package claimservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ClaimApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClaimApplication.class, args);
    }
}
