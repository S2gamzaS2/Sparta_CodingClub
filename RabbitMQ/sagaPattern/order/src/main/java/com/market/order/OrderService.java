package com.market.order;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

  private Map<UUID, Order> orderStore = new HashMap<>();


  public Order createOrder(OrderEndpoint.OrderRequestDto dto) {
    Order order = dto.toOrder();

    orderStore.put(order.getOrderId(), order);
    return order;
  }

  public Order getOrder(UUID orderId) {
    return orderStore.get(orderId);
  }

}
