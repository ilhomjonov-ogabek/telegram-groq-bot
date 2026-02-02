package uz.airline.uzairlinebookingsystem.dto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Getter;

@Getter
public class AppErrorDTO {

  private String errorPath;
  private String errorMessage;
  private Integer errorCode;
  private LocalDateTime timestamp;

  public AppErrorDTO(String errorPath, String errorMessage, Integer errorCode) {
    this.errorPath = errorPath;
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
    this.timestamp = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Tashkent")));
  }
}
