package com.nathangibson.portfolio.mapper;

import com.nathangibson.portfolio.domain.Stock;
import com.nathangibson.portfolio.domain.Transaction;
import com.nathangibson.portfolio.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

  public Transaction mapTransactionEntityToTransaction(
      TransactionEntity transactionEntity) {
    Transaction transaction = new Transaction();
    transaction.setStockId(transactionEntity.getStockId());
    transaction.setQuantity(transactionEntity.getQuantity());
    transaction.setTimestamp(transactionEntity.getTimestamp());
    transaction.setType(transactionEntity.getType());
    transaction.setPrice(transactionEntity.getPrice());
    return transaction;
  }
}
