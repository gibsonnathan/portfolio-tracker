package com.nathangibson.portfolio.domain;

import lombok.Data;

@Data
public class Position {
  private Stock stock;
  private double quantity;
  private double averageCost;

}
