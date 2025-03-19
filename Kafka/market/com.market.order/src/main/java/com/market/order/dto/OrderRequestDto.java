package com.market.order.dto;

import com.market.order.entity.Order;
import com.market.order.message.DeliveryMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderRequestDto {
    private String userId;
    private Integer productId;
    private Integer productQuantity;
    private Integer payAmount;


    public Order toOrder() {
        return Order.builder()
                .orderId(UUID.randomUUID())
                .userId(userId)
                .orderStatus("RECEIPT")
                .build();
    }

    public DeliveryMessage toDeliveryMessage(UUID orderId) {
        return DeliveryMessage.builder()
                .orderId(orderId)
                .productId(productId)
                .productQuantity(productQuantity)
                .payAmount(payAmount)
                .build();
    }
}