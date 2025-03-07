package com.example.redis;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("item")
public class Item implements Serializable {
    @Id
    private String id; // Id를 String으로 쓰면 UUID 자동 배정

    private String name;

    private String description;

    private Integer price;
}
