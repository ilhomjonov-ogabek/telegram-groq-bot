package com.project.entity;

import com.project.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
  private Integer id;
  private String name;
  private String username;
  private String password;
  private Role role;



}
