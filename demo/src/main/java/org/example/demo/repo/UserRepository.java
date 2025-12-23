package org.example.demo.repo;

import java.util.Optional;
import org.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User> findByChatId(Long chatId);

  Optional<User> findFirstByChatId(Long chatId);

  void findByPhoneNumber(String phoneNumber);

}
