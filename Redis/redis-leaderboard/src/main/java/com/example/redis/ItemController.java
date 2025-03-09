package com.example.redis;

import com.example.redis.domain.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("{id}/purchase")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void purchase(
            @PathVariable("id")
            Long id
    ) {
        itemService.purchase(id);
    }

    @GetMapping("/ranks")
    public List<ItemDto> getRanks() {
        return itemService.getMostSold();
    }
}
