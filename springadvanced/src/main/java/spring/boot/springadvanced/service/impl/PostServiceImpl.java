package spring.boot.springadvanced.service.impl;

import jakarta.transaction.Transactional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
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
  private final CacheManager cacheManager;
  private final Cache cache;

  public PostServiceImpl(PostRepository postRepository, CacheManager cacheManager) {
    this.postRepository = postRepository;
    this.cacheManager = cacheManager;
    this.cache = cacheManager.getCache("posts");
  }


  @Override
  @Transactional
  public ResponseEntity<Post> createUser(PostCreateDTO dto) {
    return null;
  }

  @Override
  @SneakyThrows
  public Post get(Integer id)  {
    Post cashedPost = cache.get(id, Post.class);
    if(cashedPost != null) {
      return cashedPost;
    }
    Post post =  postRepository.findById(id).orElseThrow(()-> new RuntimeException("Post not found"));
    TimeUnit.SECONDS.sleep(1);
    cache.put(id, post);
    return post;
  }

  @Override
  @SneakyThrows
  public void delete(Integer id)  {
    cache.evict(id);
    postRepository.deleteById(id);
  }

  @Override
  public void update(PostUpdateDTO dto) {
    Post post = get(dto.getId());
    post.setTitle(dto.getTitle());
    post.setBody(dto.getBody());
    postRepository.save(post);

    cache.put(dto.getId(), post);
  }
}
