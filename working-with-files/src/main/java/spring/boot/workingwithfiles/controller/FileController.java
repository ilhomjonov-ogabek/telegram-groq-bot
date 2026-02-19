package spring.boot.workingwithfiles.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.workingwithfiles.service.FileService;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

  private final FileService fileService;

  @GetMapping("/pdf")
  public ResponseEntity<Resource> getFile() {
    return fileService.convertFile();
  }

  @GetMapping("/word")
  public ResponseEntity<Resource> getWord() {
    return fileService.getFile();
  }

}
