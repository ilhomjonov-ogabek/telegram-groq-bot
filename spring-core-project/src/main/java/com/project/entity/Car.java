package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Car {
  private Integer id;
  private String model;
  private String color;
  private Integer price;

}
