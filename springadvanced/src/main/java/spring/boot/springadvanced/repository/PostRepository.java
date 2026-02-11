package spring.boot.springadvanced.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.springadvanced.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}