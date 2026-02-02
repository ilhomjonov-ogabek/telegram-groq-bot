package uz.airline.uzairlinebookingsystem.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import uz.airline.uzairlinebookingsystem.dto.BookingDTO;
import uz.airline.uzairlinebookingsystem.dto.SearchingDTO;
import uz.airline.uzairlinebookingsystem.dto.UserDTO;
import uz.airline.uzairlinebookingsystem.dto.UserSignDTO;
import uz.airline.uzairlinebookingsystem.dto.AviationResponseDTO;
import uz.airline.uzairlinebookingsystem.entity.UserEntity;
import uz.airline.uzairlinebookingsystem.enums.RoleEnum;
import uz.airline.uzairlinebookingsystem.jwtConfig.JwtProvider;
import uz.airline.uzairlinebookingsystem.repository.UserRepository;
import uz.airline.uzairlinebookingsystem.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;
  private final RestClient restClient;

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
  public ResponseEntity<?> searchBooking(SearchingDTO bookingDTO) {

    AviationResponseDTO response = restClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("${aviationstack.base-url}")
            .queryParam("access_key", "${aviationstack.api-key}")
            .queryParam("flight_iata", bookingDTO.getToIata())
            .build())
        .retrieve()
        .body(AviationResponseDTO.class);

    List<BookingDTO> bookings = response.getData();

    return ResponseEntity.ok(bookings);
  }

}
