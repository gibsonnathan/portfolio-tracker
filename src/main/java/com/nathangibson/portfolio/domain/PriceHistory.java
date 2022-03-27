package com.nathangibson.portfolio.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Data
public class PriceHistory {
  private Map<LocalDate, Double> priceByDate = new TreeMap<>();

  public Double addPriceForDate(LocalDate date, Double price) {
    return priceByDate.put(date, price);
  }

  public LocalDate getEarliestDate() {
    return priceByDate.keySet().stream()
        .reduce(LocalDate.MAX, (acc, curr) -> curr.isBefore(acc) ? curr : acc);
  }

  public Double getPriceForDate(LocalDate date) {
    return priceByDate.containsKey(date) ? priceByDate.get(date) :
        getPreviousPrice(date);
  }

  private Double getPreviousPrice(LocalDate date) {
    Optional<LocalDate> previousDate =
        priceByDate.keySet().stream().filter(d -> d.isBefore(date))
            .reduce((first, last) -> last);
    return previousDate.isPresent() ? getPriceForDate(previousDate.get()) : 0;
  }
}
