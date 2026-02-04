package uz.airline.uzairlinebookingsystem.dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class BookingDTO implements Serializable {

  private String flightNumber;
  private String airlineName;
  private String depIata;
  private String arrIata;
  private OffsetDateTime depScheduled;
  private OffsetDateTime arrScheduled;
  private String status;
  private BigDecimal price;
}