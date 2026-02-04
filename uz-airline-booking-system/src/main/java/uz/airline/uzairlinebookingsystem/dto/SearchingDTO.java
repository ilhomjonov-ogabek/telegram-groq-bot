package uz.airline.uzairlinebookingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link uz.airline.uzairlinebookingsystem.entity.BookingEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchingDTO implements Serializable {

  @NotBlank(message = "depIata bo'sh bo'lmasligi kerak")
  private String depIata;

  @NotBlank(message = "arrIata bo'sh bo'lmasligi kerak")
  private String arrIata;

  private OffsetDateTime depScheduled;

}