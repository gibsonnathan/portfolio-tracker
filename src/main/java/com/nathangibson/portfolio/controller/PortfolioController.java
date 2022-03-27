package com.nathangibson.portfolio.controller;

import com.nathangibson.portfolio.domain.Portfolio;
import com.nathangibson.portfolio.request.CreatePositionRequest;
import com.nathangibson.portfolio.response.PortfolioResponse;
import com.nathangibson.portfolio.service.PortfolioService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PortfolioController {

  private PortfolioService portfolioService;

  public PortfolioController(PortfolioService portfolioService) {
    this.portfolioService = portfolioService;
  }

  @GetMapping("/portfolio/{username}")
  public PortfolioResponse getPortfolioForUser(@PathVariable String username) {
    Portfolio portfolio = portfolioService.getPortfolioForUsername(username);
    PortfolioResponse portfolioResponse = new PortfolioResponse();
    portfolioResponse.setPortfolio(portfolio);
    portfolioResponse.setErrorCode(null);
    portfolioResponse.setErrorDescription(null);
    return portfolioResponse;
  }

  @PostMapping("/portfolio/{username}/positions")
  public PortfolioResponse addPositionToPortfolio(@PathVariable String username,
                                                  @RequestBody
                                                      CreatePositionRequest createPositionRequest) {
    portfolioService.addPositionToUsersPortfolio(username,
        createPositionRequest);
    Portfolio portfolio = portfolioService.getPortfolioForUsername(username);
    PortfolioResponse portfolioResponse = new PortfolioResponse();
    portfolioResponse.setPortfolio(portfolio);
    portfolioResponse.setErrorCode(null);
    portfolioResponse.setErrorDescription(null);
    return portfolioResponse;
  }
}
