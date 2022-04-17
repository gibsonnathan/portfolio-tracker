package com.nathangibson.portfolio.mapper;

import com.nathangibson.portfolio.domain.Stock;
import com.nathangibson.portfolio.entity.StockEntity;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

  public Stock mapStockEntityToStock(StockEntity stockEntity) {
    Stock stock = new Stock();
    stock.setTicker(stockEntity.getTicker());
    stock.setId(stock.getId());
    return stock;
  }
}
