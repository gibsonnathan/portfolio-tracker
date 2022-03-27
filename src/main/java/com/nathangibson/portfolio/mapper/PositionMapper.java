package com.nathangibson.portfolio.mapper;

import com.nathangibson.portfolio.domain.Position;
import com.nathangibson.portfolio.domain.Stock;
import com.nathangibson.portfolio.domain.User;
import com.nathangibson.portfolio.entity.PositionEntity;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper {

  public Position mapPositionEntityToPosition(PositionEntity positionEntity,
                                              Stock stock) {
    Position position = new Position();
    position.setStock(stock);
    position.setQuantity(positionEntity.getQuantity());
    position.setAverageCost(positionEntity.getAverageCost());
    return position;
  }
}
