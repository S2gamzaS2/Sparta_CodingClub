package com.spring_cloud.eureka.client.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Product Client 호출
@FeignClient(name="product-service")
public interface ProductClient {

    @GetMapping("/product/{id}")
    String getProduct(@PathVariable("id") String id);
}
