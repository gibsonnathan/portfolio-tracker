package com.nathangibson.portfolio.repository;

import com.nathangibson.portfolio.entity.PriceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceHistoryRepository
    extends JpaRepository<PriceHistoryEntity, Long> {

  List<PriceHistoryEntity> findByStockId(long stockId);
}
