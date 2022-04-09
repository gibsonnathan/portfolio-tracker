package com.nathangibson.portfolio.domain;

import com.nathangibson.portfolio.entity.PriceHistoryEntity;
import com.nathangibson.portfolio.service.PriceHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PortfolioTests {

  private PriceHistoryService priceHistoryService;

  @BeforeEach
  public void init() {
    priceHistoryService = Mockito.mock(PriceHistoryService.class);
  }

  @Test
  public void canAddTransactionToPortfolio() {
    Portfolio portfolio = new Portfolio();
    Transaction transaction = new Transaction();
    Stock stock = new Stock();
    stock.setTicker("test");
    transaction.setQuantity(1);
    transaction.setStock(stock);
    transaction.setTimestamp(
        LocalDate.of(2022, 1, 2).atStartOfDay().toInstant(ZoneOffset.UTC));

    PriceHistoryEntity priceHistoryEntity = new PriceHistoryEntity();
    priceHistoryEntity.setPrice(123.0);
    priceHistoryEntity.setTimestamp(
        LocalDate.of(2022, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC));
    Mockito.when(priceHistoryService.getPriceHistoryForStock("test"))
        .thenReturn(List.of(priceHistoryEntity));

    portfolio.addTransaction(transaction, priceHistoryService);

    List<Transaction> transactions = portfolio.getTransactions();
    assertTrue(transactions.contains(transaction));

    Map<Instant, Double> prices = portfolio.getPriceByInstantMap();
    assertEquals(123.0, prices.get(
        LocalDate.of(2022, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC)));
  }

  @Test
  public void canAddMultipleTransactions() {
    Portfolio portfolio = new Portfolio();

    Transaction firstTransaction = new Transaction();
    Stock firstStock = new Stock();
    firstStock.setTicker("x");
    firstTransaction.setQuantity(1);
    firstTransaction.setStock(firstStock);
    firstTransaction.setTimestamp(
        LocalDate.of(2022, 1, 2).atStartOfDay().toInstant(ZoneOffset.UTC));

    Transaction secondTransaction = new Transaction();
    Stock secondStock = new Stock();
    secondStock.setTicker("y");
    secondTransaction.setQuantity(1);
    secondTransaction.setStock(secondStock);
    secondTransaction.setTimestamp(
        LocalDate.of(2022, 1, 2).atStartOfDay().toInstant(ZoneOffset.UTC));

    PriceHistoryEntity priceHistoryEntityForX = new PriceHistoryEntity();
    priceHistoryEntityForX.setPrice(100.0);
    priceHistoryEntityForX.setTimestamp(
        LocalDate.of(2022, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC));
    Mockito.when(priceHistoryService.getPriceHistoryForStock("x"))
        .thenReturn(List.of(priceHistoryEntityForX));

    PriceHistoryEntity priceHistoryEntityForY = new PriceHistoryEntity();
    priceHistoryEntityForY.setPrice(100.0);
    priceHistoryEntityForY.setTimestamp(
        LocalDate.of(2022, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC));
    Mockito.when(priceHistoryService.getPriceHistoryForStock("y"))
        .thenReturn(List.of(priceHistoryEntityForY));

    portfolio.addTransaction(firstTransaction, priceHistoryService);
    portfolio.addTransaction(secondTransaction, priceHistoryService);

    List<Transaction> transactions = portfolio.getTransactions();
    assertTrue(transactions.contains(firstTransaction));
    assertTrue(transactions.contains(secondTransaction));

    Map<Instant, Double> prices = portfolio.getPriceByInstantMap();
    assertEquals(200.0, prices.get(
        LocalDate.of(2022, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC)));
  }

  @Test
  public void doNotIncludePriceBeforeTransaction() {
    Portfolio portfolio = new Portfolio();

    Transaction transaction = new Transaction();
    Stock firstStock = new Stock();
    firstStock.setTicker("x");
    transaction.setQuantity(1);
    transaction.setStock(firstStock);
    transaction.setTimestamp(
        LocalDate.of(2022, 1, 2).atStartOfDay().toInstant(ZoneOffset.UTC));

    PriceHistoryEntity priceHistory = new PriceHistoryEntity();
    priceHistory.setPrice(100.0);
    priceHistory.setTimestamp(
        LocalDate.of(2022, 1, 3).atStartOfDay().toInstant(ZoneOffset.UTC));
    Mockito.when(priceHistoryService.getPriceHistoryForStock("x"))
        .thenReturn(List.of(priceHistory));

    portfolio.addTransaction(transaction, priceHistoryService);

    Map<Instant, Double> prices = portfolio.getPriceByInstantMap();
    assertNull(prices.get(
        LocalDate.of(2022, 1, 2).atStartOfDay().toInstant(ZoneOffset.UTC)));
  }
}
