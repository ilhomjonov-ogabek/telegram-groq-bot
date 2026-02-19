package spring.boot.apitest.service;

import java.time.Duration;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Service
public class PostService {


  public ResponseEntity<?> getPost(String id) {
    WebClient client = WebClient.builder()
        .baseUrl("http://localhost:8080/api/posts")
        .clientConnector(new ReactorClientHttpConnector(
            HttpClient.create()
                .responseTimeout(Duration.ofSeconds(6))
        )) 
        .build();

    String block = client.get()
        .uri("/"+id)
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return ResponseEntity.ok().body(block);
  }
}
