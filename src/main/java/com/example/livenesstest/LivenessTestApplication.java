package com.example.livenesstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class LivenessTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LivenessTestApplication.class, args);
        System.out.println(context);
    }

}
