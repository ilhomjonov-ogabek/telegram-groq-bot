package uz.airline.uzairlinebookingsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.airline.uzairlinebookingsystem.enums.BookingStatus;

@Getter
@Setter
@Entity
@Table(name = "booking_entity")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @NotNull
  private Long userId;
  private String flightNumber;
  private String airlineName;
  private String depIata;
  private String arrIata;
  private OffsetDateTime depScheduled;
  private OffsetDateTime arrScheduled;
  private OffsetDateTime bookedAt;
  private BookingStatus status;
  private Boolean booked;
  private BigDecimal price;



}