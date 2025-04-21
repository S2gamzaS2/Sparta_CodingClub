package com.market.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEndpoint {

  public final PaymentService paymentService;

  @RabbitListener(queues = "${message.queue.payment}")
  public void receiveMessage(DeliveryMessage deliveryMessage) {

    log.info("******** PAYMENT MESSAGE: {}", deliveryMessage.toString());

    paymentService.createPayment(deliveryMessage);
  }

}
