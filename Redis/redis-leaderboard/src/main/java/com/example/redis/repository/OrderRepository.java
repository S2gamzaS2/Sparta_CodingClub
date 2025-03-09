package com.example.redis.repository;

import com.example.redis.domain.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ItemOrder, Long> { }
