package com.example.redis.domain;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;
    @Setter
    private String category;
}
