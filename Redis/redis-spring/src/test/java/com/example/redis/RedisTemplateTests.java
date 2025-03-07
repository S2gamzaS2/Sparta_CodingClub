package com.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTemplateTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, ItemDto> itemRedisTemplate;

    @Test
    public void stringOpsTest() {

        // 문자열 조작을 위한 클래스
        ValueOperations<String, String> ops
                // 지금 RedisTemplate에 설정된 타입을 바탕으로 Redis 문자열 조작을 할 거다
                = stringRedisTemplate.opsForValue();

        ops.set("simplekey", "simplevalue");
        System.out.println(ops.get("simplekey"));


        // 집합을 조작하기 위한 클래스
        SetOperations<String, String> setOps
                = stringRedisTemplate.opsForSet();

        setOps.add("hoboies", "games");
        setOps.add("hoboies", "coding", "alcohol");

        System.out.println(setOps.members("hoboies"));

        stringRedisTemplate.expire("hoboies", 10, TimeUnit.SECONDS);
        stringRedisTemplate.delete("simplekey");
    }


    @Autowired
    private RedisTemplate<String, ItemDto> ItemRedisTemplate;

    @Test
    public void itemRedisTemplateTest() {
        ValueOperations<String, ItemDto> ops
                = itemRedisTemplate.opsForValue();

        ops.set("my:keyboard", ItemDto.builder()
                .name("시끄러운키보드")
                .description("엄청 시끄러워서 못쓰겠음")
                .price(20000)
                .build());

        System.out.println(ops.get("my:keyboard"));

        ops.set("my:mouse", ItemDto.builder()
                .name("투명마우스")
                .description("마우스 없음 필요함")
                .price(10000)
                .build());

        System.out.println(ops.get("my:mouse"));
    }
}
