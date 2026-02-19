package uz.airline.learningapi.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.airline.learningapi.service.ExternalApiService;

import java.nio.file.Path;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
public class ExternalApiController {

  @GetMapping("/posts")
  public ResponseEntity<List<String>> getPosts() {
  }

}
