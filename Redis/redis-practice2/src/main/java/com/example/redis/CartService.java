package com.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class CartService {
    private final String keyString = "cart:%s";
    private final RedisTemplate<String, String> cartTemplate;
    private final HashOperations<String, String, Integer> hashOps;

    public CartService(RedisTemplate<String, String> cartTemplate) {
        this.cartTemplate = cartTemplate;
        this.hashOps = this.cartTemplate.opsForHash();
    }

    public void modifyCart(String sessionId, CartItemDto dto) {
        hashOps.increment(
                keyString.formatted(sessionId),
                dto.getItem(),
                dto.getCount()
        );
        int count = Optional.ofNullable(hashOps.get(keyString.formatted(sessionId), dto.getItem()))
                .orElse(0);
        if (count <= 0) {
            hashOps.delete(keyString.formatted(sessionId), dto.getItem());
        }
    }

    public CartDto getCart(String sessionId) {
        boolean exists = Optional.ofNullable(cartTemplate.hasKey(keyString.formatted(sessionId)))
                .orElse(false);
        if (!exists)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Date expireAt = Date.from(Instant.now().plus(30, ChronoUnit.SECONDS));
        cartTemplate.expireAt(
                keyString.formatted(sessionId),
                expireAt
        );
        return CartDto.fromHashPairs(
                hashOps.entries(keyString.formatted(sessionId)),
                expireAt
        );
    }
}
