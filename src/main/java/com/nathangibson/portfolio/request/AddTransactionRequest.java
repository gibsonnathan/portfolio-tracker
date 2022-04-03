package com.nathangibson.portfolio.request;

import lombok.Data;

import java.time.Instant;

@Data
public class AddTransactionRequest {

  private String ticker;
  private Double quantity;
  private Instant purchaseTimestamp;
}
