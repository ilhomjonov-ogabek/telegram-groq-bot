package spring.boot.springadvanced.service.impl;

import jakarta.transaction.Transactional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spring.boot.springadvanced.dto.PostCreateDTO;
import spring.boot.springadvanced.dto.PostUpdateDTO;
import spring.boot.springadvanced.entity.Post;
import spring.boot.springadvanced.repository.PostRepository;
import spring.boot.springadvanced.service.PostService;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final ConcurrentHashMap<Integer, Post> postsCashe = new ConcurrentHashMap<>();



  @Override
  @Transactional
  public ResponseEntity<Post> createUser(PostCreateDTO dto) {
    return null;
  }

  @Override
  @SneakyThrows
  public Post get(Integer id)  {
    Post cashedPost = postsCashe.get(id);
    if(cashedPost != null) {
      return cashedPost;
    }
    Post post =  postRepository.findById(id).orElseThrow(()-> new RuntimeException("Post not found"));
    TimeUnit.SECONDS.sleep(1);
    postsCashe.put(id, post);
    return post;
  }

  @Override
  @SneakyThrows
  public void delete(Integer id)  {
    postsCashe.remove(id);
    postRepository.deleteById(id);
  }

  @Override
  public void update(PostUpdateDTO dto) {
    Post post = get(dto.getId());
    post.setTitle(dto.getTitle());
    post.setBody(dto.getBody());
    postRepository.save(post);

    postsCashe.put(dto.getId(), post);
  }
}
