package com.market.payment;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

  private final RabbitTemplate rabbitTemplate;

  @Value("${message.queue.err.product}")
  private String productErrQueue;

  public void createPayment(DeliveryMessage deliveryMessage) {

    Payment payment = Payment.builder()
        .paymentId(UUID.randomUUID())
        .userId(deliveryMessage.getUserId())
        .payAmount(deliveryMessage.getPayAmount())
        .payStatus("SUCCESS")
        .build();

    // 결제 금액이 10000원 이상이면 에러
    if(deliveryMessage.getPayAmount() >= 10000) {
      log.error("#### Payment Amount Exceeds Limit: {}", payment.getPayAmount());
      deliveryMessage.setErrorType("PAYMENT_LIMIT_EXCEEDED");
      payment.setPayStatus("FAILED");

      this.rollbackPayment(deliveryMessage, payment.getPaymentId());
    }
  }


  // RabbitTemplate을 통해 에러 큐로 보내는 함수
  public void rollbackPayment(DeliveryMessage deliveryMessage, UUID paymentId) {
    log.info("***** PAYMENT ROLLBACK!!!!");
    deliveryMessage.setPaymentId(paymentId);
    rabbitTemplate.convertAndSend(productErrQueue, deliveryMessage);
  }

}
