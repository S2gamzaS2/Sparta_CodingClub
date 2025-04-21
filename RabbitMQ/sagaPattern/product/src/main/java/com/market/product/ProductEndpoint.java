package com.market.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEndpoint {

  private final ProductService productService;

  @RabbitListener(queues = "${message.queue.product}")
  public void receiveMessage(DeliveryMessage deliveryMessage) {

    log.info("********** PRODUCT RECEIVE: {}", deliveryMessage.toString());
    productService.reduceProductAmount(deliveryMessage);
  }

}
