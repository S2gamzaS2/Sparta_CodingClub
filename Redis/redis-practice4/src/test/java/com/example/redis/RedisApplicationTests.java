package com.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisApplicationTests {
	@Autowired
	private RedisTemplate<String, String> cartTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void test() {
		HashOperations<String, String, Integer> hashOperations = cartTemplate.opsForHash();
		hashOperations.put("user:alex", "mouse", 10);
		System.out.println(hashOperations.get("user:alex", "mouse"));
		cartTemplate.expire("user:alex", 60 * 60 * 3, TimeUnit.SECONDS);
	}
}
