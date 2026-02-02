package uz.airline.uzairlinebookingsystem.service;

import org.springframework.http.ResponseEntity;
import uz.airline.uzairlinebookingsystem.dto.SearchingDTO;
import uz.airline.uzairlinebookingsystem.dto.UserDTO;
import uz.airline.uzairlinebookingsystem.dto.UserSignDTO;

public interface UserService {

  ResponseEntity<?> singUpUser(UserDTO userDTO);

  ResponseEntity<?> signInUser(UserSignDTO userDTO);

  ResponseEntity<?> searchBooking(SearchingDTO bookingDTO);
}
