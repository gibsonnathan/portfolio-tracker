package com.nathangibson.portfolio.domain;

import lombok.Data;

import java.time.Instant;

@Data
public class Transaction {
  private String type;
  private long stockId;
  private double quantity;
  private Instant timestamp;
  private double price;
}
