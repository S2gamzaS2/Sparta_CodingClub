package com.market.payment;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

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
      payment.setPayStatus("FAILED");
      return;
    }
  }

}
