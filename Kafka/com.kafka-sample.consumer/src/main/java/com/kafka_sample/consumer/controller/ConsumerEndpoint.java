package com.kafka_sample.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerEndpoint {

    @KafkaListener(groupId = "group_a", topics = "topic1")
    public void consumeFromGroupA1(String message) {
        log.info("Group A consumed message from topic 1 : " + message);
    }

    @KafkaListener(groupId = "group_b", topics = "topic1")
    public void consumeFromGroupB1(String message) {
        log.info("Group B consumed message from topic 1 : " + message);
    }

    @KafkaListener(groupId = "group_c", topics = "topic2")
    public void consumeFromGroupC2(String message) {
        log.info("Group C consumed message from topic 2 : " + message);
    }

    @KafkaListener(groupId = "group_c", topics = "topic3")
    public void consumeFromGroupC3(String message) {
        log.info("Group C consumed message from topic 3 : " + message);
    }

    @KafkaListener(groupId = "group_d", topics = "topic4")
    public void consumeFromGroupD4(String message) {
        log.info("Group D consumed message from topic 4 : " + message);
    }
}
