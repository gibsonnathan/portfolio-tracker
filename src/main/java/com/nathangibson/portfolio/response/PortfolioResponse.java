package com.nathangibson.portfolio.response;

import com.nathangibson.portfolio.domain.Portfolio;
import lombok.Data;

@Data
public class PortfolioResponse extends Response {
  private Portfolio portfolio;
}
