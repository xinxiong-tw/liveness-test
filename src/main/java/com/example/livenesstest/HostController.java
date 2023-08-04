package com.example.livenesstest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.InetAddress;

@RestController
@RequestMapping("/")
public class HostController {
    @GetMapping
    public Mono<String> getHost() {
        System.out.println("host");
        return Mono.fromCallable(() -> InetAddress.getLocalHost().getHostName())
                .subscribeOn(Schedulers.boundedElastic());
    }

}
