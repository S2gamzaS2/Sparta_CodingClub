package com.market.product.message;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryMessage {

    // 큐를 통해 받을 메세지

    private String userId;
    private UUID orderId;
    private UUID paymentId;
    private String errorType;

    private Integer productId;
    private Integer productQuantity;
    private Integer payAmount;
}
