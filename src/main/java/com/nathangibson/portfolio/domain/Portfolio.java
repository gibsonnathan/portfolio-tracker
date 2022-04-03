package com.nathangibson.portfolio.domain;

import com.nathangibson.portfolio.entity.PriceHistoryEntity;
import com.nathangibson.portfolio.service.PriceHistoryService;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

@Data
public class Portfolio {
  private User user;
  private List<Transaction> transactions = new ArrayList<>();
  private Map<LocalDate, Double> priceByDateMap = new TreeMap<>();

  public boolean addPosition(Transaction transaction,
                             PriceHistoryService priceHistoryService) {
    Stock stock = transaction.getStock();
    String ticker = stock.getTicker();
    List<PriceHistoryEntity> priceHistoryEntities =
        priceHistoryService.getPriceHistoryForStock(ticker);
    priceHistoryEntities.forEach(priceHistoryEntity -> {
      LocalDate closeDate = priceHistoryEntity.getCloseDate();
      Double price = priceHistoryEntity.getPrice();
      if (priceByDateMap.containsKey(closeDate)) {
        Double current = priceByDateMap.get(closeDate);
        priceByDateMap.put(closeDate,
            current + (transaction.getQuantity() * price));
      } else {
        priceByDateMap.put(closeDate, transaction.getQuantity() * price);
      }
    });
    return transactions.add(transaction);
  }

  public List<Transaction> getTransactions() {
    return Collections.unmodifiableList(transactions);
  }

  public Map<LocalDate, Double> getPriceByDateMap() {
    return Collections.unmodifiableMap(priceByDateMap);
  }
}
