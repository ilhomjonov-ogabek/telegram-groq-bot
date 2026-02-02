package org.example.springsecurity.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {

  private Long id;
  private String username;
  private String password;
  private Role role;


}
