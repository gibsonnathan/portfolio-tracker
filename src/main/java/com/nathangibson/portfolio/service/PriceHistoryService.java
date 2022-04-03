package com.nathangibson.portfolio.service;

import com.nathangibson.portfolio.entity.PriceHistoryEntity;
import com.nathangibson.portfolio.entity.StockEntity;
import com.nathangibson.portfolio.exception.StockNotFoundException;
import com.nathangibson.portfolio.repository.PriceHistoryRepository;
import com.nathangibson.portfolio.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceHistoryService {

  private PriceHistoryRepository priceHistoryRepository;
  private StockRepository stockRepository;

  public PriceHistoryService(PriceHistoryRepository priceHistoryRepository,
                             StockRepository stockRepository) {
    this.priceHistoryRepository = priceHistoryRepository;
    this.stockRepository = stockRepository;
  }

  public List<PriceHistoryEntity> getPriceHistoryForStock(String ticker) {
    StockEntity stockEntity = stockRepository.findByTicker(ticker)
        .orElseThrow(() -> new StockNotFoundException());
    return priceHistoryRepository.findByStockId(stockEntity.getId());
  }
}
