package com.nathangibson.portfolio.domain;

import com.nathangibson.portfolio.entity.PriceHistoryEntity;
import com.nathangibson.portfolio.service.PriceHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PortfolioTests {

  private PriceHistoryService priceHistoryService;

  @BeforeEach
  public void init() {
    priceHistoryService = Mockito.mock(PriceHistoryService.class);
  }

  @Test
  public void canAddPositionToPortfolio() {
    Portfolio portfolio = new Portfolio();
    Transaction transaction = new Transaction();
    Stock stock = new Stock();
    stock.setTicker("test");
    transaction.setQuantity(1);
    transaction.setStock(stock);

    PriceHistoryEntity priceHistoryEntity = new PriceHistoryEntity();
    priceHistoryEntity.setPrice(123.0);
    priceHistoryEntity.setCloseDate(LocalDate.of(2022, 1, 1));
    Mockito.when(priceHistoryService.getPriceHistoryForStock("test"))
        .thenReturn(List.of(priceHistoryEntity));

    portfolio.addPosition(transaction, priceHistoryService);

    List<Transaction> transactions = portfolio.getTransactions();
    assertTrue(transactions.contains(transaction));

    Map<LocalDate, Double> prices = portfolio.getPriceByDateMap();
    assertEquals(123.0, prices.get(LocalDate.of(2022, 1, 1)));
  }

  @Test
  public void canAddMultiplePositions() {
    Portfolio portfolio = new Portfolio();

    Transaction firstTransaction = new Transaction();
    Stock firstStock = new Stock();
    firstStock.setTicker("x");
    firstTransaction.setQuantity(1);
    firstTransaction.setStock(firstStock);

    Transaction secondTransaction = new Transaction();
    Stock secondStock = new Stock();
    secondStock.setTicker("y");
    secondTransaction.setQuantity(1);
    secondTransaction.setStock(secondStock);

    PriceHistoryEntity priceHistoryEntityForX = new PriceHistoryEntity();
    priceHistoryEntityForX.setPrice(100.0);
    priceHistoryEntityForX.setCloseDate(LocalDate.of(2022, 1, 1));
    Mockito.when(priceHistoryService.getPriceHistoryForStock("x"))
        .thenReturn(List.of(priceHistoryEntityForX));

    PriceHistoryEntity priceHistoryEntityForY = new PriceHistoryEntity();
    priceHistoryEntityForY.setPrice(100.0);
    priceHistoryEntityForY.setCloseDate(LocalDate.of(2022, 1, 1));
    Mockito.when(priceHistoryService.getPriceHistoryForStock("y"))
        .thenReturn(List.of(priceHistoryEntityForY));

    portfolio.addPosition(firstTransaction, priceHistoryService);
    portfolio.addPosition(secondTransaction, priceHistoryService);

    List<Transaction> transactions = portfolio.getTransactions();
    assertTrue(transactions.contains(firstTransaction));
    assertTrue(transactions.contains(secondTransaction));

    Map<LocalDate, Double> prices = portfolio.getPriceByDateMap();
    assertEquals(200.0, prices.get(LocalDate.of(2022, 1, 1)));
  }
}
