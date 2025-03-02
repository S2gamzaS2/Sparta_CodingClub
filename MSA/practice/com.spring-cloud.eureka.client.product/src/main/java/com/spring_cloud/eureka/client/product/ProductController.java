package com.spring_cloud.eureka.client.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/product")
    public String getProduct() {
        return "Server Port: " + serverPort + ")";
    }
}
