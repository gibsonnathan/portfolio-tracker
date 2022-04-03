package com.nathangibson.portfolio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nathangibson.portfolio.entity.PriceHistoryEntity;
import com.nathangibson.portfolio.service.PriceHistoryService;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

@Data
public class Portfolio {
  private User user;
  private List<Position> positions = new ArrayList<>();
  private Map<LocalDate, Double> priceByDate = new TreeMap<>();

  public boolean addPosition(Position position,
                             PriceHistoryService priceHistoryService) {
    Stock stock = position.getStock();
    String ticker = stock.getTicker();
    List<PriceHistoryEntity> priceHistoryEntities =
        priceHistoryService.getPriceHistoryForStock(ticker);
    priceHistoryEntities.forEach(priceHistoryEntity -> {
      LocalDate closeDate = priceHistoryEntity.getCloseDate();
      Double price = priceHistoryEntity.getPrice();
      if (priceByDate.containsKey(closeDate)) {
        Double current = priceByDate.get(closeDate);
        priceByDate.put(closeDate, current + (position.getQuantity() * price));
      } else {
        priceByDate.put(closeDate, position.getQuantity() * price);
      }
    });
    return positions.add(position);
  }

  @JsonIgnore
  public List<Position> getPositions() {
    return Collections.unmodifiableList(positions);
  }

  @JsonIgnore
  public Map<LocalDate, Double> getPrices() {
    return Collections.unmodifiableMap(priceByDate);
  }
}
