package com.example.redis.domain;


import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto implements Serializable {
    private Long id;
    private String name;
    private String category;

    public static StoreDto fromEntity(Store entity) {
        return StoreDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .build();
    }
}
