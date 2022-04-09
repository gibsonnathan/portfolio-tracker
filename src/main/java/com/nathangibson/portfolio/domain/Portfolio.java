package com.nathangibson.portfolio.domain;

import com.nathangibson.portfolio.entity.PriceHistoryEntity;
import com.nathangibson.portfolio.service.PriceHistoryService;
import lombok.Data;

import java.time.Instant;
import java.util.*;

@Data
public class Portfolio {
  private User user;
  private List<Transaction> transactions = new ArrayList<>();
  private Map<Instant, Double> priceByInstantMap = new TreeMap<>();

  public boolean addTransaction(Transaction transaction,
                                PriceHistoryService priceHistoryService) {
    // TODO: handle sell transaction
    Stock stock = transaction.getStock();
    String ticker = stock.getTicker();
    Instant transactionTimestamp = transaction.getTimestamp();
    List<PriceHistoryEntity> priceHistoryEntities =
        priceHistoryService.getPriceHistoryForStock(ticker);
    priceHistoryEntities.forEach(priceHistoryEntity -> {
      Instant priceHistoryTimestamp = priceHistoryEntity.getTimestamp();
      // TODO: handle whether or not the stock was owned at the time of the
      //  price
      Double price = priceHistoryEntity.getPrice();
      if (priceByInstantMap.containsKey(priceHistoryTimestamp)) {
        Double current = priceByInstantMap.get(priceHistoryTimestamp);
        priceByInstantMap.put(priceHistoryTimestamp,
            current + (transaction.getQuantity() * price));
      } else {
        priceByInstantMap.put(priceHistoryTimestamp,
            transaction.getQuantity() * price);
      }
    });
    return transactions.add(transaction);
  }

  public List<Transaction> getTransactions() {
    return Collections.unmodifiableList(transactions);
  }

  public Map<Instant, Double> getPriceByInstantMap() {
    return Collections.unmodifiableMap(priceByInstantMap);
  }
}
