package io.samsorg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@Configuration
@SpringBootApplication
public class DreamApiWithDataApplication {
    public static void main(String[] args) {
		SpringApplication.run(DreamApiWithDataApplication.class, args);
	}
}
