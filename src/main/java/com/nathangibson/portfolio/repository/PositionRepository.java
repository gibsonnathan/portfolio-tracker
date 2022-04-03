package com.nathangibson.portfolio.repository;

import com.nathangibson.portfolio.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository
    extends JpaRepository<TransactionEntity, Long> {

  public List<TransactionEntity> findByUserId(long userId);
}
