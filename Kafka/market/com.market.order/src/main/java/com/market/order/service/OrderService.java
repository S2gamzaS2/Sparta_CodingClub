package com.market.order.service;

import com.market.order.dto.OrderRequestDto;
import com.market.order.entity.Order;
import com.market.order.message.DeliveryMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private Map<UUID, Order> orderStore = new HashMap<>();

    private final RabbitTemplate rabbitTemplate;
    @Value("${message.queue.product}")
    private String productQueue;

    public Order createOrder(OrderRequestDto dto) {
        Order order = dto.toOrder();
        orderStore.put(order.getOrderId(), order);

        // order가 생성되면 product에 product count를 줄이라고 메세지 보내기
        DeliveryMessage deliveryMessage = dto.toDeliveryMessage(order.getOrderId());
        rabbitTemplate.convertAndSend(productQueue,  deliveryMessage);

        return order;
    }


    public Order getOrder(UUID orderId) {
        return orderStore.get(orderId);
    }
}
