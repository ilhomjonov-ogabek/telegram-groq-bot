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
public class BookingEntityDTO implements Serializable {

  private String flightIata;
  private String flightNumber;
  private String airlineIata;
  private String airlineName;
  private String fromIata;
  private String toIata;
  private String fromAirportName;
  private String toAirportName;
  private OffsetDateTime departureTime;
  private OffsetDateTime arrivalTime;
}