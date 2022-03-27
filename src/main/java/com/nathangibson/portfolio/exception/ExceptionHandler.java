package com.nathangibson.portfolio.exception;

import com.nathangibson.portfolio.response.PortfolioResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(
      PortfolioNotFoundException.class)
  public PortfolioResponse handlePortfolioNotFoundException(
      Exception exception) {
    PortfolioResponse portfolioResponse = new PortfolioResponse();
    portfolioResponse.setErrorDescription("Portfolio not found.");
    portfolioResponse.setErrorCode("PORTFOLIO_NOT_FOUND");
    return portfolioResponse;
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(
      UserNotFoundException.class)
  public PortfolioResponse handleUserNotFoundException(Exception exception) {
    PortfolioResponse portfolioResponse = new PortfolioResponse();
    portfolioResponse.setErrorDescription("User not found.");
    portfolioResponse.setErrorCode("USER_NOT_FOUND");
    return portfolioResponse;
  }
}
