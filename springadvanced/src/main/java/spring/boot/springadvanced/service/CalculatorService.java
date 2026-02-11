package spring.boot.springadvanced.service;

import org.springframework.stereotype.Service;

public interface CalculatorService {

  double add(double a, double b);
  double div(double a, double b);
  double sub(double a, double b);
  double mul(double a, double b);

}
