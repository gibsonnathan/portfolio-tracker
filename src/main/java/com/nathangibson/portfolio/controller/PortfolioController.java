package com.nathangibson.portfolio.controller;

import com.nathangibson.portfolio.domain.Portfolio;
import com.nathangibson.portfolio.domain.User;
import com.nathangibson.portfolio.request.AddTransactionRequest;
import com.nathangibson.portfolio.response.PortfolioResponse;
import com.nathangibson.portfolio.service.PortfolioService;
import com.nathangibson.portfolio.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PortfolioController {

  private PortfolioService portfolioService;
  private UserService userService;

  public PortfolioController(PortfolioService portfolioService,
                             UserService userService) {
    this.portfolioService = portfolioService;
    this.userService = userService;
  }

  @GetMapping("/portfolios/{username}")
  public PortfolioResponse getPortfolioForUser(@PathVariable String username) {
    User user = userService.getUserByUsername(username);
    Portfolio portfolio = portfolioService.getPortfolioForUser(user.getId());
    PortfolioResponse portfolioResponse = new PortfolioResponse();
    portfolioResponse.setPortfolio(portfolio);
    portfolioResponse.setErrorCode(null);
    portfolioResponse.setErrorDescription(null);
    return portfolioResponse;
  }

  @PostMapping("/portfolios/{username}/transactions")
  public PortfolioResponse addPositionToPortfolio(@PathVariable String username,
                                                  @RequestBody
                                                  AddTransactionRequest addTransactionRequest) {
    User user = userService.getUserByUsername(username);
    long userId = user.getId();
    portfolioService.addTransactionToUser(userId, addTransactionRequest);
    Portfolio portfolio = portfolioService.getPortfolioForUser(userId);
    PortfolioResponse portfolioResponse = new PortfolioResponse();
    portfolioResponse.setPortfolio(portfolio);
    portfolioResponse.setErrorCode(null);
    portfolioResponse.setErrorDescription(null);
    return portfolioResponse;
  }
}
