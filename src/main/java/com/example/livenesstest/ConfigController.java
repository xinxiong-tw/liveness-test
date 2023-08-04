package com.example.livenesstest;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/config")
public class ConfigController {

    private final Environment environment;

    public ConfigController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping
    public Mono<String> getAllConfigs() {
        return Mono.just(((AbstractEnvironment) environment).getPropertySources().stream()
                .map(PropertySource::getSource)
                .map(Object::toString)
                .collect(Collectors.joining("\n")));
    }

    @GetMapping("/{key}")
    public Mono<String> getConfigOf(@PathVariable String key) {
        var name = environment.getProperty(key);
        return Optional.ofNullable(name).map(Mono::just).orElse(Mono.just("no config for " + key));
    }
}
