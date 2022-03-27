package com.nathangibson.portfolio.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "stocks")
public class StockEntity {
  @Id
  private long id;
  private String ticker;
}
