package org.example.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.Entity;
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
