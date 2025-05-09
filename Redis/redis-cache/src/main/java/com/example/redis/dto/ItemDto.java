package com.example.redis.dto;

import com.example.redis.entity.Item;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Integer price;

    public static ItemDto fromEntity(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .build();

    }
}
