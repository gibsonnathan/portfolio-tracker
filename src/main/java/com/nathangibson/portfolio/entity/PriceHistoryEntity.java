package com.nathangibson.portfolio.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "price_history")
public class PriceHistoryEntity {
  @Id
  private long id;
  private long stockId;
  private LocalDate closeDate;
  private Double price;
}
