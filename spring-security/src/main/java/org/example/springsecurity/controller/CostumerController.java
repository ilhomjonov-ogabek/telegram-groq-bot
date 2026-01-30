package org.example.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CostumerController {

  @RequestMapping("/dashboard")
  public ResponseEntity<?> getCustomerDashboard() {
    return ResponseEntity.ok("this is customer dashboard");
  }
}
