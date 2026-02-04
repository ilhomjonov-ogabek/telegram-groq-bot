package uz.airline.uzairlinebookingsystem.service.impl;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import uz.airline.uzairlinebookingsystem.dto.BookingDTO;
import uz.airline.uzairlinebookingsystem.dto.SearchingDTO;
import uz.airline.uzairlinebookingsystem.dto.UserDTO;
import uz.airline.uzairlinebookingsystem.dto.UserSignDTO;
import uz.airline.uzairlinebookingsystem.dto.apidto.AviationStackResponseDTO;
import uz.airline.uzairlinebookingsystem.entity.BookingEntity;
import uz.airline.uzairlinebookingsystem.entity.UserEntity;
import uz.airline.uzairlinebookingsystem.enums.BookingStatus;
import uz.airline.uzairlinebookingsystem.enums.RoleEnum;
import uz.airline.uzairlinebookingsystem.jwtConfig.JwtProvider;
import uz.airline.uzairlinebookingsystem.repository.BookingRepository;
import uz.airline.uzairlinebookingsystem.repository.UserRepository;
import uz.airline.uzairlinebookingsystem.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;
  private final RestClient restClient;
  private final BookingRepository bookingRepository;

  @Value("${aviationstack.base-url}")
  private String baseUrl;

  @Value("${aviationstack.api-key}")
  private String apiKey;

  @Override
  public ResponseEntity<?> singUpUser(UserDTO userDTO) {
    if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body("Email already exists: " + userDTO.getEmail());
    }

    UserEntity user = UserEntity.builder()
        .firstname(userDTO.getFirstname())
        .lastname(userDTO.getLastname())
        .email(userDTO.getEmail())
        .password(passwordEncoder.encode(userDTO.getPassword()))
        .role(RoleEnum.USER)
        .gender(userDTO.getGender())
        .createdAt(LocalDateTime.now())
        .build();

    userRepository.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body("Successfully signed up");
  }

  @Override
  public ResponseEntity<?> signInUser(UserSignDTO userDTO) {
    UserEntity user = userRepository.findByEmail(userDTO.getEmail())
        .orElse(null);

    if (user == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("User not found: " + userDTO.getEmail());
    }

    if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body("Invalid email or password");
    }

    String token = jwtProvider.generateToken(
        user.getEmail(),
        Map.of("role", user.getRole().name())
    );

    return ResponseEntity.ok(Map.of(
        "token", token,
        "email", user.getEmail(),
        "role", user.getRole().name()
    ));
  }

  @Override
  public ResponseEntity<?> searchFlight(SearchingDTO searchingDTO) {

    AviationStackResponseDTO apiResponse = restClient.get()
        .uri(uriBuilder -> uriBuilder
            .scheme("https")
            .host("api.aviationstack.com")
            .path("/v1/flights")
            .queryParam("access_key", apiKey)
            .queryParam("dep_iata", searchingDTO.getDepIata())
            .queryParam("arr_iata", searchingDTO.getArrIata())
            /*.queryParam("flight_date",
                searchingDTO.getDepartureTime().toLocalDate().toString())*/
            .build())
        .retrieve()
        .body(AviationStackResponseDTO.class);

    if (apiResponse == null || apiResponse.getData() == null) {
      return ResponseEntity.ok(List.of());
    }

    List<BookingDTO> flights = apiResponse.getData().stream()
        .map(item -> {
          BookingDTO dto = new BookingDTO();

          dto.setFlightNumber(item.getFlight() != null ? item.getFlight().getIata() : null);
          dto.setAirlineName(item.getAirline() != null ? item.getAirline().getName() : null);

          dto.setDepIata(item.getDeparture() != null ? item.getDeparture().getIata() : null);
          dto.setArrIata(item.getArrival() != null ? item.getArrival().getIata() : null);

          if (item.getDeparture() != null && item.getDeparture().getScheduled() != null) {
            dto.setDepScheduled(OffsetDateTime.parse(item.getDeparture().getScheduled()));
          }
          if (item.getArrival() != null && item.getArrival().getScheduled() != null) {
            dto.setArrScheduled(OffsetDateTime.parse(item.getArrival().getScheduled()));
          }

          dto.setStatus(BookingStatus.SCHEDULED.toString());
          dto.setPrice(null);

          return dto;
        })
        .toList();
    List<BookingDTO> responses = new ArrayList<>();

    flights.forEach(flight -> {
      if (flight.getDepScheduled().equals(searchingDTO.getDepScheduled()) && flight.getDepIata().equals(searchingDTO.getDepIata()) && flight.getArrIata().equals(searchingDTO.getArrIata())) {
        responses.add(flight);
      }
    });
    return ResponseEntity.ok(responses);
  }

  @Override
  public ResponseEntity<?> bookingFlight(BookingDTO bookingDTO) {
   /* Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) auth.getPrincipal();
    String email = userDetails.getUsername();

    Optional<UserEntity> byEmail = userRepository.findByEmail(email);
*/

    System.out.println(bookingDTO);

    BookingEntity booking = BookingEntity
        .builder()
        .userId(1L)
        .airlineName(bookingDTO.getAirlineName())
        .flightNumber(bookingDTO.getFlightNumber())
        .arrScheduled(bookingDTO.getArrScheduled())
        .depScheduled(bookingDTO.getDepScheduled())
        .depIata(bookingDTO.getDepIata())
        .arrIata(bookingDTO.getArrIata())
        .status(BookingStatus.SCHEDULED)
        .booked(false)
        .price(bookingDTO.getPrice())
        .bookedAt(OffsetDateTime.now())
        .build();

    bookingRepository.save(booking);

    return ResponseEntity.ok(booking);
  }


}
