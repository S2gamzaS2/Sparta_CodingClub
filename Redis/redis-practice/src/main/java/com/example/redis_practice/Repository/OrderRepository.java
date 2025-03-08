package com.example.redis_practice.Repository;

import com.example.redis_practice.Entity.ItemOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<ItemOrder, String> {
}
