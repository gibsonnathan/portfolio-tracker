package com.nathangibson.portfolio.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
  @Id
  private long id;
  private String username;

}
