package com.example.redis;

import com.example.redis.domain.ItemDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, ItemDto> rankTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, ItemDto> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.json());

        return template;
    }

    @Bean
    public ZSetOperations<String, ItemDto> zsetOps(RedisTemplate<String, ItemDto> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

}
