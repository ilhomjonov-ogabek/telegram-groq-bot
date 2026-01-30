package org.example.springsecurity.controller;

import java.util.Map;
import lombok.NoArgsConstructor;
import org.example.springsecurity.entity.dto.UserAuthDTO;
import org.example.springsecurity.jwtConfig.JwtProvider;
import org.example.springsecurity.repository.RoleRepository;
import org.example.springsecurity.repository.UserRepository;
import org.example.springsecurity.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@NoArgsConstructor(force = true)
public class AuthController {

  final UserRepository userRepository;
  final PasswordEncoder passwordEncoder;
  final RoleRepository roleRepository;
  final JwtProvider jwtProvider;

  @PostMapping("/sign-up")
  public ResponseEntity<?> signUp(@RequestBody UserAuthDTO userDto) {
    User user = User
        .builder()
        .username(userDto.username())
        .password(passwordEncoder.encode(userDto.password()))
        .role(roleRepository.findById(2l).orElseThrow())
        .build();
    userRepository.save(user);
    return ResponseEntity.ok("Muvaffaqiyatli saqlandi");

  }

  @PostMapping("/sign-in")
  public ResponseEntity<?> signIn(@RequestBody UserAuthDTO userDto) {
    User user = userRepository.findByUsername(userDto.username());
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    if (passwordEncoder.matches(userDto.password(), userDto.password())) {
      String token = jwtProvider.generateToken(user.getUsername(), Map.of());
      return ResponseEntity.ok().body(token);
    }
    return ResponseEntity.ok("something is wrong");
  }
}
