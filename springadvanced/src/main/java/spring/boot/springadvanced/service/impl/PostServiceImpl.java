package spring.boot.springadvanced.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spring.boot.springadvanced.dto.PostCreateDTO;
import spring.boot.springadvanced.dto.PostUpdateDTO;
import spring.boot.springadvanced.entity.Post;
import spring.boot.springadvanced.repository.PostRepository;
import spring.boot.springadvanced.service.PostService;

@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;


  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;

  }


  @Override
  @Transactional
  public ResponseEntity<Post> createUser(PostCreateDTO dto) {
    return null;
  }

  @Override
  @SneakyThrows
  @Cacheable(value = "posts",key = "#id")
  public Post get(Integer id)  {
    Post post =  postRepository.findById(id).orElseThrow(()-> new RuntimeException("Post not found"));
    TimeUnit.SECONDS.sleep(1);
    return post;
  }

  @Override
  @SneakyThrows
  @CacheEvict(value = "posts",key = "#id")
  public void delete(Integer id)  {
    postRepository.deleteById(id);
  }

  @Override
  @CachePut(value = "posts",key = "#dto.id")
  public Post update(PostUpdateDTO dto) {
    Post post = get(dto.getId());
    post.setTitle(dto.getTitle());
    post.setBody(dto.getBody());
    postRepository.save(post);
    return post;
  }

  @Override
  @Cacheable(value = "posts",key = "#root.methodName")
  public List<Post> getAll() {
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return postRepository.findAll();
  }
}
