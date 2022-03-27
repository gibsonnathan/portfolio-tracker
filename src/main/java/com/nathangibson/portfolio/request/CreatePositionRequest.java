package com.nathangibson.portfolio.request;

import lombok.Data;

@Data
public class CreatePositionRequest {

  private String ticker;
  private Double quantity;
  private Double averageCost;
}
