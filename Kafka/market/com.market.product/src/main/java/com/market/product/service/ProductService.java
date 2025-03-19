package com.market.product.service;

import com.market.product.message.DeliveryMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${message.queue.payment}")
    private String paymentQueue;

    public void reduceProductAmount(DeliveryMessage deliveryMessage) {
        Integer productId = deliveryMessage.getProductId();
        Integer productQuantity = deliveryMessage.getProductQuantity();

        if(productId != 1 || productQuantity > 1) {
            return;
        }

        // 수량 수정하고 난 후 payment에게 보내기
        rabbitTemplate.convertAndSend(paymentQueue, deliveryMessage);


    }
}
