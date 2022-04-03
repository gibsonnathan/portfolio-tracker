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
    Position position = new Position();
    Stock stock = new Stock();
    stock.setTicker("test");
    position.setQuantity(1);
    position.setAverageCost(100);
    position.setAverageCost(10);
    position.setStock(stock);

    PriceHistoryEntity priceHistoryEntity = new PriceHistoryEntity();
    priceHistoryEntity.setPrice(123.0);
    priceHistoryEntity.setCloseDate(LocalDate.of(2022, 1, 1));
    Mockito.when(priceHistoryService.getPriceHistoryForStock("test"))
        .thenReturn(List.of(priceHistoryEntity));

    portfolio.addPosition(position, priceHistoryService);

    List<Position> positions = portfolio.getPositions();
    assertTrue(positions.contains(position));

    Map<LocalDate, Double> prices = portfolio.getPriceByDateMap();
    assertEquals(123.0, prices.get(LocalDate.of(2022, 1, 1)));
  }

  @Test
  public void canAddMultiplePositions() {
    Portfolio portfolio = new Portfolio();

    Position firstPosition = new Position();
    Stock firstStock = new Stock();
    firstStock.setTicker("x");
    firstPosition.setQuantity(1);
    firstPosition.setAverageCost(100);
    firstPosition.setAverageCost(10);
    firstPosition.setStock(firstStock);

    Position secondPosition = new Position();
    Stock secondStock = new Stock();
    secondStock.setTicker("y");
    secondPosition.setQuantity(1);
    secondPosition.setAverageCost(100);
    secondPosition.setAverageCost(10);
    secondPosition.setStock(secondStock);

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

    portfolio.addPosition(firstPosition, priceHistoryService);
    portfolio.addPosition(secondPosition, priceHistoryService);

    List<Position> positions = portfolio.getPositions();
    assertTrue(positions.contains(firstPosition));
    assertTrue(positions.contains(secondPosition));

    Map<LocalDate, Double> prices = portfolio.getPriceByDateMap();
    assertEquals(200.0, prices.get(LocalDate.of(2022, 1, 1)));
  }
}
