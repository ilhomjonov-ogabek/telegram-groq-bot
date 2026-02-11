package spring.boot.springadvanced.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import spring.boot.springadvanced.dto.PostCreateDTO;
import spring.boot.springadvanced.dto.PostUpdateDTO;
import spring.boot.springadvanced.entity.Post;

public interface PostService {

  ResponseEntity<Post> createUser(PostCreateDTO dto);

  Post get(Integer id) throws InterruptedException;

  void delete(Integer id);

  Post update(PostUpdateDTO dto);

  List<Post> getAll();
}
