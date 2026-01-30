package org.example.springsecurity.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.springsecurity.entity.enums.RoleEnum;

public class Role {

  private Long id;

  @Enumerated(EnumType.STRING)
  private RoleEnum role;

}
