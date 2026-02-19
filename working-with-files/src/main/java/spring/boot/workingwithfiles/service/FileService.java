package spring.boot.workingwithfiles.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface FileService {

  ResponseEntity<Resource> convertFile();

  ResponseEntity<Resource> getFile();
}
