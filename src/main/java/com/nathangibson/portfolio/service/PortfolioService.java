package com.nathangibson.portfolio.service;

import com.nathangibson.portfolio.domain.Portfolio;
import com.nathangibson.portfolio.domain.Stock;
import com.nathangibson.portfolio.entity.TransactionEntity;
import com.nathangibson.portfolio.request.AddTransactionRequest;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {

  private final StockService stockService;
  private final TransactionService transactionService;

  public PortfolioService(StockService stockService,
                          TransactionService transactionService) {
    this.stockService = stockService;
    this.transactionService = transactionService;
  }

  public Portfolio getPortfolioForUser(long id) {
    Portfolio portfolio = new Portfolio();
    transactionService.getTransactionsForUser(id).forEach(transaction -> {
      if (transaction.getType().equalsIgnoreCase("BUY")) {
        portfolio.addBuyTransaction(transaction);
      } else if (transaction.getType().equalsIgnoreCase("SELL")) {
        portfolio.addSellTransaction(transaction);
      } else {
        throw new RuntimeException("Unhandled transaction type.");
      }
    });
    return portfolio;
  }

  public TransactionEntity addTransactionToUser(long id,
                                                AddTransactionRequest addTransactionRequest) {

    Stock stock =
        stockService.getStockByTicker(addTransactionRequest.getTicker());
    TransactionEntity transactionEntity = new TransactionEntity();
    transactionEntity.setUserId(id);
    transactionEntity.setStockId(stock.getId());
    transactionEntity.setQuantity(addTransactionRequest.getQuantity());
    transactionEntity.setTimestamp(
        addTransactionRequest.getPurchaseTimestamp());
    transactionService.saveTransactionEntity(transactionEntity);

    return transactionEntity;
  }
}
