package com.nathangibson.portfolio.service;

import com.nathangibson.portfolio.domain.Portfolio;
import com.nathangibson.portfolio.domain.Position;
import com.nathangibson.portfolio.domain.Stock;
import com.nathangibson.portfolio.domain.User;
import com.nathangibson.portfolio.entity.PositionEntity;
import com.nathangibson.portfolio.entity.StockEntity;
import com.nathangibson.portfolio.entity.UserEntity;
import com.nathangibson.portfolio.exception.StockNotFoundException;
import com.nathangibson.portfolio.exception.UserNotFoundException;
import com.nathangibson.portfolio.mapper.PositionMapper;
import com.nathangibson.portfolio.mapper.StockMapper;
import com.nathangibson.portfolio.mapper.UserMapper;
import com.nathangibson.portfolio.repository.PositionRepository;
import com.nathangibson.portfolio.repository.StockRepository;
import com.nathangibson.portfolio.repository.UserRepository;
import com.nathangibson.portfolio.request.CreatePositionRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

  private final PositionRepository positionRepository;
  private final StockRepository stockRepository;
  private final UserRepository userRepository;
  private final PositionMapper positionMapper;
  private final StockMapper stockMapper;
  private final UserMapper userMapper;
  private final PriceHistoryService priceHistoryService;

  public PortfolioService(PositionRepository positionRepository,
                          StockRepository stockRepository,
                          UserRepository userRepository,
                          PositionMapper positionMapper,
                          StockMapper stockMapper, UserMapper userMapper,
                          PriceHistoryService priceHistoryService) {
    this.positionRepository = positionRepository;
    this.stockRepository = stockRepository;
    this.userRepository = userRepository;
    this.positionMapper = positionMapper;
    this.stockMapper = stockMapper;
    this.userMapper = userMapper;
    this.priceHistoryService = priceHistoryService;
  }

  public Portfolio getPortfolioForUsername(String username) {
    Portfolio portfolio = new Portfolio();
    UserEntity userEntity = userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException());
    User user = userMapper.mapUserEntityToUser(userEntity);
    List<PositionEntity> positionEntities =
        positionRepository.findByUserId(userEntity.getId());
    positionEntities.forEach(positionEntity -> {
      StockEntity stockEntity =
          stockRepository.getById(positionEntity.getStockId());
      Stock stock = stockMapper.mapStockEntityToStock(stockEntity);
      Position position =
          positionMapper.mapPositionEntityToPosition(positionEntity, stock);
      portfolio.addPosition(position, priceHistoryService);
    });
    portfolio.setUser(user);

    return portfolio;
  }

  public PositionEntity addPositionToUsersPortfolio(String username,
                                                    CreatePositionRequest createPositionRequest) {
    UserEntity userEntity = userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException());
    StockEntity stockEntity =
        stockRepository.findByTicker(createPositionRequest.getTicker())
            .orElseThrow(() -> new StockNotFoundException());
    PositionEntity positionEntity = new PositionEntity();
    positionEntity.setUserId(userEntity.getId());
    positionEntity.setStockId(stockEntity.getId());
    positionEntity.setAverageCost(createPositionRequest.getAverageCost());
    positionEntity.setQuantity(createPositionRequest.getQuantity());
    positionRepository.save(positionEntity);

    return positionEntity;
  }
}
