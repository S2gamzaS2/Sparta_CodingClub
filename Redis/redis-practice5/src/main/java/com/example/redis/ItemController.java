package com.example.redis;

import com.example.redis.domain.ItemDto;
import com.example.redis.domain.ItemOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ItemDto create(
            @RequestBody
            ItemDto itemDto
    ) {
        return itemService.create(itemDto);
    }

    @GetMapping
    public List<ItemDto> readAll() {
        return itemService.readAll();
    }

    @GetMapping("{id}")
    public ItemDto readOne(
            @PathVariable("id")
            Long id
    ) {
        return itemService.readOne(id);
    }

    @PutMapping("{id}")
    public ItemDto update(
            @PathVariable("id")
            Long id,
            @RequestBody
            ItemDto dto
    ) {
        return itemService.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable
            Long id
    ) {
        itemService.delete(id);
    }

    @GetMapping("search")
    public Page<ItemDto> search(
            @RequestParam(name = "q")
            String query,
            Pageable pageable
    ) {
        return itemService.searchByName(query, pageable);
    }

    @PostMapping("{id}/purchase")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void purchase(
            @RequestBody
            ItemOrderDto dto
    ) {
        itemService.purchase(dto);
    }

    @GetMapping("/ranks")
    public List<ItemDto> getRanks() {
        return itemService.getMostSold();
    }
}
