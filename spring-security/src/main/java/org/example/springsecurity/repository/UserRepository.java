package org.example.springsecurity.repository;

import org.example.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String username);

  User findUserByUsername(String username);
}
