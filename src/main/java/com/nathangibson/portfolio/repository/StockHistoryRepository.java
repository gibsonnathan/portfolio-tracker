package com.nathangibson.portfolio.repository;

import com.nathangibson.portfolio.entity.StockHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockHistoryRepository
    extends JpaRepository<StockHistoryEntity, Long> {

  List<StockHistoryEntity> findByStockId(long stockId);
}
