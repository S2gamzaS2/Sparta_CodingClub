package com.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisRepositoryTests {
    @Autowired
    private ItemRepository itemRepository;


    @Test
    public void createTest() {
        // Item 객체 생성
        Item item = Item.builder()
                .name("keyboard100")
                .description("완전 좋은 키보드")
                .price(12000)
                .build();
        // save 호출
        itemRepository.save(item);
    }

    @Test
    public void readOneTest() {
        Item item = itemRepository.findById("")
                .orElseThrow();

        System.out.println(item.getDescription());
    }

    @Test
    public void updateTest() {
        Item item = itemRepository.findById("")
                .orElseThrow();
        item.setDescription("Update Description : On Sale!!");
        item = itemRepository.save(item);
        System.out.println(item.getDescription());
    }

    @Test
    public void deleteTest() {
        itemRepository.deleteById("");
    }
}
