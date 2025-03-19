package com.market.order.message;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryMessage {

    private UUID orderId;
    private UUID paymentId;
    private String errorType;


    // 큐로 보낼 메세지
    private String userId;
    private Integer productId;
    private Integer productQuantity;
    private Integer payAmount;
}
