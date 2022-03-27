package com.nathangibson.portfolio.mapper;

import com.nathangibson.portfolio.domain.PriceHistory;
import com.nathangibson.portfolio.domain.Stock;
import com.nathangibson.portfolio.entity.PriceHistoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceHistoryMapper {

  public PriceHistory mapPriceHistoryEntitiesToPriceHistory(
      List<PriceHistoryEntity> priceHistoryEntities) {
    PriceHistory priceHistory = new PriceHistory();
    priceHistoryEntities.forEach(priceHistoryEntity -> {
      priceHistory.addPriceForDate(priceHistoryEntity.getCloseDate(),
          priceHistoryEntity.getPrice());
    });
    return priceHistory;
  }
}
