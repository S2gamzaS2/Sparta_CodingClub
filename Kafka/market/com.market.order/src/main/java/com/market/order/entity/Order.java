package com.market.order.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Builder
@Data
@ToString
public class Order {

    private UUID orderId;
    private String userId;
    private String orderStatus;
    private String errorType;

    public void cancelOrder(String receiverErrorType) {
        orderStatus = "CANCELLED";
        errorType = receiverErrorType;
    }
}
