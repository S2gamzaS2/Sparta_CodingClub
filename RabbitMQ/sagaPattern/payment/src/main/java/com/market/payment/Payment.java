package com.market.payment;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {

  private UUID paymentId;
  private String userId;

  private Integer payAmount;

  private String payStatus;

}
