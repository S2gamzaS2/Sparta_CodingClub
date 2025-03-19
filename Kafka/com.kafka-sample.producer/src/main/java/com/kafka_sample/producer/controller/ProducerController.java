package com.kafka_sample.producer.controller;

import com.kafka_sample.producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping("/send")
    public String sendMessage(@RequestParam("topic") String topic,
                              @RequestParam("key") String key,
                              @RequestParam("message") String message) {

        producerService.sendMassage(topic, key, message);
        return "카프카에서 메세지 보냄";
    }
}
