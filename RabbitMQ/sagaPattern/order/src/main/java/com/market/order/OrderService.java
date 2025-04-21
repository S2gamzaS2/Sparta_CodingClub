package com.market.order;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

  private final RabbitTemplate rabbitTemplate;

  @Value("${message.queue.product}")
  private String productQueue;


  private Map<UUID, Order> orderStore = new HashMap<>();


  public Order createOrder(OrderEndpoint.OrderRequestDto dto) {
    Order order = dto.toOrder();
    orderStore.put(order.getOrderId(), order);

    DeliveryMessage deliveryMessage = dto.toDeliveryMessage(order.getOrderId());

    // RabbitMQ
    rabbitTemplate.convertAndSend(productQueue, deliveryMessage);

    return order;
  }


  public Order getOrder(UUID orderId) {
    return orderStore.get(orderId);
  }


  public void rollbackOrder(DeliveryMessage deliveryMessage) {

    Order order = orderStore.get(deliveryMessage.getOrderId());
    order.cancelOrder(deliveryMessage.getErrorType());
  }
}
