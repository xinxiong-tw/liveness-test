package com.example.livenesstest;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AnyController {
    @RequestMapping("/**")
    public Mono<String> hello(ServerHttpRequest request) {
        return Mono.just("Hello " + request.getPath());
    }
}
