package com.nathangibson.portfolio.domain;

import lombok.Data;

import java.time.Instant;

@Data
public class Transaction {
  private Stock stock;
  private double quantity;
  private Instant purchaseTimestamp;

}
