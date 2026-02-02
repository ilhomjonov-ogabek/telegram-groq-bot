package uz.airline.uzairlinebookingsystem.dto;

import java.io.Serializable;
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

  private String fromIata;
  private String toIata;
  private OffsetDateTime departureTime;
}