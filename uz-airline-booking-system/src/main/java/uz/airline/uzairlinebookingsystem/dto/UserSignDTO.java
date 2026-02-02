package uz.airline.uzairlinebookingsystem.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link uz.airline.uzairlinebookingsystem.entity.UserEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignDTO implements Serializable {

  private String email;
  private String password;
}