package uz.airline.learningapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class ExternalApiService {

  private final RestClient restClient;

  public ExternalApiService(RestClient restClient) {
    this.restClient = restClient;
  }

  // 1) Tashqi API dan JSON olib keladi
  public String fetchPostsJson() {
    return restClient.get()
        .uri("https://jsonplaceholder.typicode.com/posts")
        .retrieve()
        .body(String.class);
  }

  // 2) JSON ni faylga yozadi
  public Path saveToFile(String fileName, String content) {
    try {
      Path path = Paths.get("src/main/java/uz/airline/learningapi/data.txt");
      Files.createDirectories(path.getParent());

      Files.writeString(
          path,
          content,
          StandardOpenOption.CREATE,
          StandardOpenOption.TRUNCATE_EXISTING
      );

      return path;
    } catch (IOException e) {
      throw new RuntimeException("Faylga yozishda xatolik: " + e.getMessage(), e);
    }
  }

  // 3) Hammasini birga qiladi: olib keladi + saqlaydi
  public Path fetchAndSavePosts() {
    String json = fetchPostsJson();
    return saveToFile("data.txt", json);
  }
}
