package com.nathangibson.portfolio.domain;

import lombok.Data;

@Data
public class Holding {
  private long stockId;
  private double quantity;
}
