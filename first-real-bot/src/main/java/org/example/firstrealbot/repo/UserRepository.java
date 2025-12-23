package org.example.firstrealbot.repo;

import java.util.Optional;
import org.example.firstrealbot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByChatId(Long chatId);

  void findByPhoneNumber(String phoneNumber);
}
