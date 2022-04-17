package com.nathangibson.portfolio.domain;

import lombok.Data;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class Portfolio {
  private Map<Instant, List<Holding>> holdingsByInstant = new TreeMap<>();

  public void addBuyTransaction(Transaction transaction) {
    Optional<Instant> optionalPreviousInstant =
        getPreviousHoldingsInstant(transaction.getTimestamp());
    Holding holding = new Holding();
    holding.setQuantity(transaction.getQuantity());
    holding.setStockId(transaction.getStockId());

    if (optionalPreviousInstant.isEmpty()) {
      holdingsByInstant.put(transaction.getTimestamp(), List.of(holding));
      return;
    }

    Instant previousInstant = optionalPreviousInstant.get();
    List<Holding> holdings = holdingsByInstant.get(previousInstant);
    List<Holding> newHoldings = new ArrayList<>(holdings);
    newHoldings.add(holding);
    holdingsByInstant.put(transaction.getTimestamp(), newHoldings);
  }

  public void addSellTransaction(Transaction transaction) {
    // TODO
  }

  public Map<Instant, List<Holding>> getHoldingsByInstant() {
    return Collections.unmodifiableMap(holdingsByInstant);
  }

  private Optional<Instant> getPreviousHoldingsInstant(Instant instant) {
    List<Instant> instants = holdingsByInstant.keySet().stream().sorted()
        .collect(Collectors.toList());

    if (instants.contains(instant)) {
      return Optional.of(instant);
    }

    if (instants.size() == 0) {
      return Optional.empty();
    }

    if (instants.size() == 1 && instants.get(0).isBefore(instant)) {
      return Optional.of(instants.get(0));
    }

    for (int i = 1; i < instants.size(); i++) {
      if (instants.get(i).isAfter(instant) &&
          instants.get(i - 1).isBefore(instant)) {
        return Optional.of(instants.get(i));
      }
    }
    return Optional.empty();
  }
}
