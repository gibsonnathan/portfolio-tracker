package com.nathangibson.portfolio.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "positions")
public class PositionEntity {
  @Id
  private long id;
  private long userId;
  private long stockId;
  private double quantity;
  private double averageCost;
}
