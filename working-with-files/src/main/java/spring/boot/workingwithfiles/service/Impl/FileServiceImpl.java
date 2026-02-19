package spring.boot.workingwithfiles.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import spring.boot.workingwithfiles.service.FileService;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

  private final WebClient client;

  @Override
  public ResponseEntity<Resource> convertFile() {
    return null;
  }

  @Override
  public ResponseEntity<Resource> getFile() {

    byte[] file = client.get()
        .uri("/downloads/demos/demo.docx")
        .retrieve()
        .bodyToMono(byte[].class)
        .block();

    if (file == null || file.length == 0) {
      return ResponseEntity.notFound().build();
    }

    Resource fileResource = new ByteArrayResource(file);

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"document.docx\"")
        .contentLength(file.length)
        .body(fileResource);
  }
}
