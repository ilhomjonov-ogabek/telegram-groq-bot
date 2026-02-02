package uz.airline.uzairlinebookingsystem.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.airline.uzairlinebookingsystem.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  @Query("select u from UserEntity u where u.email = ?1")
  Optional<UserEntity> findByEmail(String email);
}