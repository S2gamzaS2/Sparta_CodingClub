package com.example.redis;

import com.example.redis.domain.Item;
import com.example.redis.domain.ItemDto;
import com.example.redis.domain.ItemOrder;
import com.example.redis.repository.ItemRepository;
import com.example.redis.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final ZSetOperations<String, ItemDto> zsetOps;

    public void purchase(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        orderRepository.save(ItemOrder.builder()
                .item(item)
                .count(1)
                .build());

        ItemDto dto = ItemDto.fromEntity(item);
        zsetOps.incrementScore("soldRanks", dto, 1);
    }

    public List<ItemDto> getMostSold() {
        Set<ItemDto> ranks = zsetOps.reverseRange("soldRanks", 0, 9);
        if(ranks == null) return Collections.emptyList();
        return ranks.stream().toList();
    }

}
