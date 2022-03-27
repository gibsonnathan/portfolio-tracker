package com.nathangibson.portfolio.service;

import com.nathangibson.portfolio.domain.Portfolio;
import com.nathangibson.portfolio.domain.PriceHistory;
import com.nathangibson.portfolio.domain.Stock;
import com.nathangibson.portfolio.entity.PriceHistoryEntity;
import com.nathangibson.portfolio.entity.StockEntity;
import com.nathangibson.portfolio.exception.StockNotFoundException;
import com.nathangibson.portfolio.mapper.PriceHistoryMapper;
import com.nathangibson.portfolio.repository.PriceHistoryRepository;
import com.nathangibson.portfolio.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceHistoryService {

  private PriceHistoryRepository priceHistoryRepository;
  private PriceHistoryMapper priceHistoryMapper;
  private StockRepository stockRepository;
  private DateService dateService;

  public PriceHistoryService(PriceHistoryRepository priceHistoryRepository,
                             PriceHistoryMapper priceHistoryMapper,
                             StockRepository stockRepository,
                             DateService dateService) {
    this.priceHistoryRepository = priceHistoryRepository;
    this.priceHistoryMapper = priceHistoryMapper;
    this.stockRepository = stockRepository;
    this.dateService = dateService;
  }

  public PriceHistory getPriceHistoryForStock(String ticker) {
    StockEntity stockEntity = stockRepository.findByTicker(ticker)
        .orElseThrow(() -> new StockNotFoundException());
    List<PriceHistoryEntity> priceHistoryEntities =
        priceHistoryRepository.findByStockId(stockEntity.getId());
    return priceHistoryMapper.mapPriceHistoryEntitiesToPriceHistory(
        priceHistoryEntities);
  }

  public PriceHistory getPriceHistoryForPortfolio(Portfolio portfolio) {
    PriceHistory priceHistory = new PriceHistory();

    if (portfolio.getPositions().size() == 0) {
      return priceHistory;
    }

    // get price histories for each stock in portfolio
    List<PriceHistory> priceHistories =
        portfolio.getPositions().stream().map(position -> {
          Stock stock = position.getStock();
          String ticker = stock.getTicker();
          return getPriceHistoryForStock(ticker);
        }).collect(Collectors.toList());

    // find the earliest price
    PriceHistory earliestPriceHistory = priceHistories.stream().reduce(
        (acc, curr) -> curr.getEarliestDate().isBefore(acc.getEarliestDate()) ?
            curr : acc).orElse(priceHistories.get(0));

    // do not have any prices
    if (earliestPriceHistory.getEarliestDate()
        .isAfter(dateService.getCurrentDate())) {
      return priceHistory;
    }

    // starting at the earliest price, sum up all prices for each day

    // TODO: handle quantity
    earliestPriceHistory.getEarliestDate()
        .datesUntil(dateService.getCurrentDate()).forEach(date -> {
          Double price =
              priceHistories.stream().map(ph -> ph.getPriceForDate(date))
              .reduce(0.0, (acc, curr) -> acc + curr);
          priceHistory.addPriceForDate(date, price);
        });

    return priceHistory;
  }
}
