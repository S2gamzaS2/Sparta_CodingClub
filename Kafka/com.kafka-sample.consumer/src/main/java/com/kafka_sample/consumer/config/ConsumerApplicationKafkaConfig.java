package com.kafka_sample.consumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

// 이 클래스는 Kafka 컨슈머 설정을 위한 Spring 설정 클래스입니다.
@EnableKafka // Kafka 리스너를 활성화하는 어노테이션입니다.
@Configuration // Spring 설정 클래스로 선언하는 어노테이션입니다.
public class ConsumerApplicationKafkaConfig {

    // Kafka 컨슈머 팩토리를 생성하는 빈을 정의합니다.
    // ConsumerFactory는 Kafka 컨슈머 인스턴스를 생성하는 데 사용됩니다.
    // 각 컨슈머는 이 팩토리를 통해 생성된 설정을 기반으로 작동합니다.
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        // 컨슈머 팩토리 설정을 위한 맵을 생성합니다.
        Map<String, Object> configProps = new HashMap<>();
        // Kafka 브로커의 주소를 설정합니다.
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 메시지 키의 디시리얼라이저 클래스를 설정합니다.
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // 메시지 값의 디시리얼라이저 클래스를 설정합니다.
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // 설정된 프로퍼티로 DefaultKafkaConsumerFactory를 생성하여 반환합니다.
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    // Kafka 리스너 컨테이너 팩토리를 생성하는 빈을 정의합니다.
    // ConcurrentKafkaListenerContainerFactory는 Kafka 메시지를 비동기적으로 수신하는 리스너 컨테이너를 생성하는 데 사용됩니다.
    // 이 팩토리는 @KafkaListener 어노테이션이 붙은 메서드들을 실행할 컨테이너를 제공합니다.
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        // ConcurrentKafkaListenerContainerFactory를 생성합니다.
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        // 컨슈머 팩토리를 리스너 컨테이너 팩토리에 설정합니다.
        factory.setConsumerFactory(consumerFactory());
        // 설정된 리스너 컨테이너 팩토리를 반환합니다.
        return factory;
    }
}