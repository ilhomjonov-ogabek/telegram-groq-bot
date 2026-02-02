package uz.airline.uzairlinebookingsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import uz.airline.uzairlinebookingsystem.enums.BookingStatus;

@Getter
@Setter
@Entity
@Table(name = "booking_entity")
public class BookingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private Long userId;
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
  private OffsetDateTime bookedAt;
  private OffsetDateTime updatedAt;
  private BookingStatus status;
  private BigDecimal price;





}