package com.nathangibson.portfolio.service;

import com.nathangibson.portfolio.domain.Transaction;
import com.nathangibson.portfolio.entity.TransactionEntity;
import com.nathangibson.portfolio.mapper.TransactionMapper;
import com.nathangibson.portfolio.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

  private TransactionRepository transactionRepository;
  private TransactionMapper transactionMapper;

  public TransactionService(TransactionRepository transactionRepository,
                            TransactionMapper transactionMapper) {
    this.transactionMapper = transactionMapper;
    this.transactionRepository = transactionRepository;
  }

  public List<Transaction> getTransactionsForUser(long id) {
    return transactionRepository.findByUserId(id).stream().map(
        transactionEntity -> transactionMapper.mapTransactionEntityToTransaction(
            transactionEntity)).collect(Collectors.toList());
  }

  public TransactionEntity saveTransactionEntity(
      TransactionEntity transactionEntity) {
    return transactionRepository.save(transactionEntity);
  }
}
