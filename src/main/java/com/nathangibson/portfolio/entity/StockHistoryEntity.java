package com.nathangibson.portfolio.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Data
@Table(name = "stock_history")
public class StockHistoryEntity {
  @Id
  private long id;
  private long stockId;
  @Column(name = "ts")
  private Instant timestamp;
  private Double price;
}
