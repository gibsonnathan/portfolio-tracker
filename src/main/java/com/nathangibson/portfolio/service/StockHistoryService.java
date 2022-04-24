package com.nathangibson.portfolio.service;

import com.nathangibson.portfolio.entity.StockHistoryEntity;
import com.nathangibson.portfolio.entity.StockEntity;
import com.nathangibson.portfolio.exception.StockNotFoundException;
import com.nathangibson.portfolio.repository.StockHistoryRepository;
import com.nathangibson.portfolio.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  public Optional<StockHistoryEntity> getStockAtInstant(String ticker,
                                                        Instant instant) {
    StockEntity stockEntity = stockRepository.findByTicker(ticker)
        .orElseThrow(() -> new StockNotFoundException());
    List<StockHistoryEntity> stockHistoryEntities =
        stockHistoryRepository.findByStockId(stockEntity.getId()).stream()
            .sorted((x, y) -> {
              if (x.equals(y)) {
                return 0;
              } else if (x.getTimestamp().isAfter(y.getTimestamp())) {
                return 1;
              } else {
                return -1;
              }
            }).collect(Collectors.toList());

    for (int i = 1; i < stockHistoryEntities.size(); i++) {
      StockHistoryEntity previous = stockHistoryEntities.get(i - 1);
      StockHistoryEntity current = stockHistoryEntities.get(i);
      if (instant.isAfter(previous.getTimestamp()) &&
          current.getTimestamp().isAfter(instant)) {
        return Optional.of(current);
      }
    }

    return Optional.empty();
  }
}
