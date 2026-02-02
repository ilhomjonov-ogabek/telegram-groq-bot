package uz.airline.uzairlinebookingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uz.airline.uzairlinebookingsystem.dto.UserDTO;
import uz.airline.uzairlinebookingsystem.dto.UserSignDTO;
import uz.airline.uzairlinebookingsystem.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO) {
    return userService.singUpUser(userDTO);
  }

  @PostMapping("/sign-in")
  public ResponseEntity<?> signIn(@RequestBody UserSignDTO userDTO) {
    return userService.signInUser(userDTO);
  }
}
