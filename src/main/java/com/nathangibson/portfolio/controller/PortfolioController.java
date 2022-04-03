package com.nathangibson.portfolio.controller;

import com.nathangibson.portfolio.domain.Portfolio;
import com.nathangibson.portfolio.request.AddTransactionRequest;
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

  @PostMapping("/portfolio/{username}/transaction")
  public PortfolioResponse addPositionToPortfolio(@PathVariable String username,
                                                  @RequestBody
                                                      AddTransactionRequest addTransactionRequest) {
    portfolioService.addPositionToUsersPortfolio(username,
        addTransactionRequest);
    Portfolio portfolio = portfolioService.getPortfolioForUsername(username);
    PortfolioResponse portfolioResponse = new PortfolioResponse();
    portfolioResponse.setPortfolio(portfolio);
    portfolioResponse.setErrorCode(null);
    portfolioResponse.setErrorDescription(null);
    return portfolioResponse;
  }
}
