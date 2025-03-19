package example.market.order.controller;

import example.market.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order/{id}")
    public String order(@PathVariable("id") String id) {
        orderService.createOrder(id);
        return "Order complete";
    }
}
