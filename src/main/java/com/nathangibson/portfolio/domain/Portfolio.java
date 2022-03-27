package com.nathangibson.portfolio.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Portfolio {
  private User user;
  private List<Position> positions = new ArrayList<>();
  private PriceHistory priceHistory;

  public boolean addPosition(Position position) {
    return positions.add(position);
  }

  public List<Position> getPositions() {
    return Collections.unmodifiableList(positions);
  }
}
