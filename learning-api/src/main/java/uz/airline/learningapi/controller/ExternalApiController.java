package uz.airline.learningapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.airline.learningapi.service.ExternalApiService;

import java.nio.file.Path;
import java.util.Map;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController {

  private final ExternalApiService externalApiService;

  public ExternalApiController(ExternalApiService externalApiService) {
    this.externalApiService = externalApiService;
  }

  // 1) API dan olib kelib faylga yozadi: data/posts.json
  @GetMapping("/sync")
  public ResponseEntity<?> sync() {
    Path path = externalApiService.fetchAndSavePosts();
    return ResponseEntity.ok(
        Map.of(
            "message", "Saved successfully",
            "file", path.toString()
        )
    );
  }

  // 2) Faqat API dan o‘qib beradi (faylga yozmaydi)
  @GetMapping("/posts")
  public ResponseEntity<?> getPostsRaw() {
    String json = externalApiService.fetchPostsJson();
    return ResponseEntity.ok(json);
  }
}
