package org.example.springboot.jparepository;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface PostRepository extends JpaRepository<Post,Integer> {

  @Query(value = "from Post p where p.userId = ?1")
  List<Post> getAllPostUserId(Integer userId);

}
