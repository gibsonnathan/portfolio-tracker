package com.nathangibson.portfolio.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Data
@Table(name = "transactions")
public class TransactionEntity {
  @Id
  private long id;
  private long userId;
  private long stockId;
  private double quantity;
  @Column(name = "ts")
  private Instant timestamp;
  private String type;
}
