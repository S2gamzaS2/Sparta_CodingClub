package com.example.redis.controller;

import com.example.redis.dto.ItemDto;
import com.example.redis.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    public final ItemService itemService;

    @PostMapping("/create")
    public ItemDto createItem(@RequestBody ItemDto itemDto) {
        return itemService.create(itemDto);
    }

    @GetMapping("/lists")
    public List<ItemDto> readAll() {
        return itemService.readAll();
    }

    @GetMapping("/{id}")
    public ItemDto readOne(@PathVariable("id") Long id) {
        return itemService.readOne(id);
    }

    @PutMapping("/{id}")
    public ItemDto updateItem(@PathVariable("id") Long id,
                           @RequestBody ItemDto itemDto) {
        return itemService.update(id, itemDto);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        itemService.delete(id);
    }
}
