package org.example.springbootjpabuddy.post;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  private PostRepository postRepository;
  public PostController(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @GetMapping("/{id}")
  public Post getPost(@PathVariable Integer id) {
    return postRepository.findById(id).orElseThrow(()->new RuntimeException("Post not found"));
  }

}
