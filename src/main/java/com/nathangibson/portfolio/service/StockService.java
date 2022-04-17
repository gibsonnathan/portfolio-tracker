package com.nathangibson.portfolio.service;

import com.nathangibson.portfolio.domain.Stock;
import com.nathangibson.portfolio.exception.StockNotFoundException;
import com.nathangibson.portfolio.mapper.StockMapper;
import com.nathangibson.portfolio.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

  private final StockRepository stockRepository;
  private final StockMapper stockMapper;

  public StockService(StockRepository stockRepository,
                      StockMapper stockMapper) {
    this.stockRepository = stockRepository;
    this.stockMapper = stockMapper;
  }

  public Stock getStockByTicker(String ticker) {
    return stockMapper.mapStockEntityToStock(
        stockRepository.findByTicker(ticker)
            .orElseThrow(() -> new StockNotFoundException()));
  }
}
