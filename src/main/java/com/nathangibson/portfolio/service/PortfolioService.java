package com.nathangibson.portfolio.service;

import com.nathangibson.portfolio.domain.Portfolio;
import com.nathangibson.portfolio.domain.Transaction;
import com.nathangibson.portfolio.domain.Stock;
import com.nathangibson.portfolio.domain.User;
import com.nathangibson.portfolio.entity.TransactionEntity;
import com.nathangibson.portfolio.entity.StockEntity;
import com.nathangibson.portfolio.entity.UserEntity;
import com.nathangibson.portfolio.exception.StockNotFoundException;
import com.nathangibson.portfolio.exception.UserNotFoundException;
import com.nathangibson.portfolio.mapper.TransactionMapper;
import com.nathangibson.portfolio.mapper.StockMapper;
import com.nathangibson.portfolio.mapper.UserMapper;
import com.nathangibson.portfolio.repository.PositionRepository;
import com.nathangibson.portfolio.repository.StockRepository;
import com.nathangibson.portfolio.repository.UserRepository;
import com.nathangibson.portfolio.request.AddTransactionRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

  private final PositionRepository positionRepository;
  private final StockRepository stockRepository;
  private final UserRepository userRepository;
  private final TransactionMapper transactionMapper;
  private final StockMapper stockMapper;
  private final UserMapper userMapper;
  private final PriceHistoryService priceHistoryService;

  public PortfolioService(PositionRepository positionRepository,
                          StockRepository stockRepository,
                          UserRepository userRepository,
                          TransactionMapper transactionMapper,
                          StockMapper stockMapper, UserMapper userMapper,
                          PriceHistoryService priceHistoryService) {
    this.positionRepository = positionRepository;
    this.stockRepository = stockRepository;
    this.userRepository = userRepository;
    this.transactionMapper = transactionMapper;
    this.stockMapper = stockMapper;
    this.userMapper = userMapper;
    this.priceHistoryService = priceHistoryService;
  }

  public Portfolio getPortfolioForUsername(String username) {
    Portfolio portfolio = new Portfolio();
    UserEntity userEntity = userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException());
    User user = userMapper.mapUserEntityToUser(userEntity);
    List<TransactionEntity> positionEntities =
        positionRepository.findByUserId(userEntity.getId());
    positionEntities.forEach(transactionEntity -> {
      StockEntity stockEntity =
          stockRepository.getById(transactionEntity.getStockId());
      Stock stock = stockMapper.mapStockEntityToStock(stockEntity);
      Transaction transaction =
          transactionMapper.mapTransactionEntityToTransaction(transactionEntity, stock);
      portfolio.addPosition(transaction, priceHistoryService);
    });
    portfolio.setUser(user);

    return portfolio;
  }

  public TransactionEntity addPositionToUsersPortfolio(String username,
                                                       AddTransactionRequest addTransactionRequest) {
    UserEntity userEntity = userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException());
    StockEntity stockEntity =
        stockRepository.findByTicker(addTransactionRequest.getTicker())
            .orElseThrow(() -> new StockNotFoundException());
    TransactionEntity transactionEntity = new TransactionEntity();
    transactionEntity.setUserId(userEntity.getId());
    transactionEntity.setStockId(stockEntity.getId());
    transactionEntity.setQuantity(addTransactionRequest.getQuantity());
    transactionEntity.setPurchaseTimestamp(
        addTransactionRequest.getPurchaseTimestamp());
    positionRepository.save(transactionEntity);

    return transactionEntity;
  }
}
