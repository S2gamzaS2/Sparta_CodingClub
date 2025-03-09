package com.example.redis.service;

import com.example.redis.dto.ItemDto;
import com.example.redis.entity.Item;
import com.example.redis.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    public final ItemRepository itemRepository;

    // [Cache-Aside]

    // 이 메서드의 결과는 캐싱이 가능하다
    // cacheNames: 이 메서드로 인해 만들어질 캐시를 지칭하는 이름, 적용할 캐시 규칙을 지정하기 위한 이름
    // key: 캐시 데이터를 구분하기 위해 활용하는 값
    @Cacheable(cacheNames = "itemCache", key = "args[0]")
    public ItemDto readOne(Long id) {
        log.info("Read One: {}", id);
        return itemRepository.findById(id)
                .map(ItemDto::fromEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Cacheable(cacheNames = "itemAllCache", key = "methodName")
    public List<ItemDto> readAll() {
        return itemRepository.findAll()
                .stream()
                .map(ItemDto::fromEntity)
                .toList();
    }

    // ----------------------------------------

    // [Write-Through]

    @CachePut(cacheNames = "itemCache", key = "#result.id")
    public ItemDto create(ItemDto itemDto) {
        Item item = Item.builder()
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .price(itemDto.getPrice())
                .build();
        itemRepository.save(item);

        return ItemDto.fromEntity(item);
    }

    @CachePut(cacheNames = "itemCache", key = "args[0]")
    // 업데이트 시 readAll에 담겨있던 캐시를 지움 - 삭제 정책
    @CacheEvict(cacheNames = "itemAllCache", allEntries = true)
    public ItemDto update(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        itemRepository.save(item);
        return ItemDto.fromEntity(item);
    }


    @CacheEvict(cacheNames = {"itemAllCache", "itemCache"}, allEntries = true)
    public void delete(Long id) {
            itemRepository.deleteById(id);
    }
}
