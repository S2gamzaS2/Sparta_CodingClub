package com.example.redis.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrderDto implements Serializable {
    private Long id;
    private Long itemId;
    private Integer count;


    public static ItemOrderDto fromEntity(ItemOrder entity) {
        return ItemOrderDto.builder()
                .id(entity.getId())
                .itemId(entity.getItemId())
                .count(entity.getCount())
                .build();
    }
}
