package com.example.redis_practice.Controller;

import com.example.redis_practice.Entity.ItemOrder;
import com.example.redis_practice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;


    @PostMapping("/create")
    public ItemOrder create(
            @RequestBody ItemOrder order
    ) {
        return orderRepository.save(order);
    }

    @GetMapping("/read")
    public List<ItemOrder> readAll() {
        List<ItemOrder> orders = new ArrayList<>();
        orderRepository.findAll()
                .forEach(orders::add);
        return orders;
    }

    @GetMapping("/read/{id}")
    public ItemOrder readOne(@PathVariable("id") String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ItemOrder update(
            @PathVariable("id") String id,
            @RequestBody ItemOrder order
    ) {
        ItemOrder item = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        item.setItem(order.getItem());
        item.setCount(order.getCount());
        item.setStatus(order.getStatus());
        item.setTotalPrice(order.getTotalPrice());
        return orderRepository.save(item);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        orderRepository.deleteById(id);
    }

}
