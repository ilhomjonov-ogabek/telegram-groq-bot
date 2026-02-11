package spring.boot.springadvanced.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService{

  @Override
  public double add(double a, double b) {
    return a+b;
  }

  @Override
  public double div(double a, double b) {
    return a/b;
  }

  @Override
  public double sub(double a, double b) {
    return a-b;
  }

  @Override
  public double mul(double a, double b) {
    return a*b;
  }
}
