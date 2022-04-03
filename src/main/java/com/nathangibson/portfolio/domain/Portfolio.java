package com.nathangibson.portfolio.domain;

import com.nathangibson.portfolio.entity.PriceHistoryEntity;
import com.nathangibson.portfolio.service.PriceHistoryService;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

@Data
public class Portfolio {
  private User user;
  private List<Position> positions = new ArrayList<>();
  private Map<LocalDate, Double> priceByDateMap = new TreeMap<>();

  public boolean addPosition(Position position,
                             PriceHistoryService priceHistoryService) {
    Stock stock = position.getStock();
    String ticker = stock.getTicker();
    List<PriceHistoryEntity> priceHistoryEntities =
        priceHistoryService.getPriceHistoryForStock(ticker);
    priceHistoryEntities.forEach(priceHistoryEntity -> {
      LocalDate closeDate = priceHistoryEntity.getCloseDate();
      Double price = priceHistoryEntity.getPrice();
      if (priceByDateMap.containsKey(closeDate)) {
        Double current = priceByDateMap.get(closeDate);
        priceByDateMap.put(closeDate,
            current + (position.getQuantity() * price));
      } else {
        priceByDateMap.put(closeDate, position.getQuantity() * price);
      }
    });
    return positions.add(position);
  }

  public List<Position> getPositions() {
    return Collections.unmodifiableList(positions);
  }

  public Map<LocalDate, Double> getPriceByDateMap() {
    return Collections.unmodifiableMap(priceByDateMap);
  }
}
