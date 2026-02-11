package spring.boot.springadvanced.controller;

import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.springadvanced.dto.PostCreateDTO;
import spring.boot.springadvanced.dto.PostUpdateDTO;
import spring.boot.springadvanced.entity.Post;
import spring.boot.springadvanced.service.PostService;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService userService;
  private final PostService postService;

  @PostMapping
  public ResponseEntity<Post> create(@RequestBody PostCreateDTO dto) {
    return userService.createUser(dto);
  }

  @GetMapping("{id}")
  public Post get(@PathVariable Integer id) throws InterruptedException {
    return postService.get(id);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Integer id) {
    postService.delete(id);
  }

  @PutMapping
  public void update(@RequestBody PostUpdateDTO dto) {
    postService.update(dto);
  }

}
