package com.nathangibson.portfolio.service;

import com.nathangibson.portfolio.entity.StockHistoryEntity;
import com.nathangibson.portfolio.entity.StockEntity;
import com.nathangibson.portfolio.exception.StockNotFoundException;
import com.nathangibson.portfolio.repository.StockHistoryRepository;
import com.nathangibson.portfolio.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockHistoryService {

  private StockHistoryRepository stockHistoryRepository;
  private StockRepository stockRepository;

  public StockHistoryService(StockHistoryRepository stockHistoryRepository,
                             StockRepository stockRepository) {
    this.stockHistoryRepository = stockHistoryRepository;
    this.stockRepository = stockRepository;
  }

  public List<StockHistoryEntity> getStockHistory(String ticker) {
    StockEntity stockEntity = stockRepository.findByTicker(ticker)
        .orElseThrow(() -> new StockNotFoundException());
    return stockHistoryRepository.findByStockId(stockEntity.getId());
  }
}
