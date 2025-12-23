package org.example.springboot.jparepository;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  private final PostRepository postRepository;

  public PostController(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @GetMapping
  public List<Post> getAll() {
    return postRepository.findAll();
  }

  @GetMapping("/{userId}")
  public List<Post> getAllByCreatorID(@PathVariable Integer userId) {
    return postRepository.getAllPostUserId(userId);
  }


}

