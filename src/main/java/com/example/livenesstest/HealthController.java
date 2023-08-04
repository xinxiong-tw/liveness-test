package com.example.livenesstest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/healthz")
public class HealthController {

    private static final long startTime = System.currentTimeMillis();
    private final ConfigurableEnvironment environment;

    public HealthController(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @Value("${deadTime:30000}")
    int deadTime;

    @GetMapping
    public Mono<ResponseEntity<?>> healthz() {
        System.out.println(deadTime);
        environment.getPropertySources().stream().forEach(propertySource -> {
            System.out.println(propertySource.getSource());
        });
        if (System.currentTimeMillis() - startTime > deadTime) {
            return Mono.just(ResponseEntity.internalServerError().build());
        }
        return Mono.just(ResponseEntity.ok().build());
    }
}
