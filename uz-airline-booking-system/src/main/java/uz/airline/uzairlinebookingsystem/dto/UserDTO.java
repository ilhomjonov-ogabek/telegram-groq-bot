package uz.airline.uzairlinebookingsystem.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.airline.uzairlinebookingsystem.enums.GenderEnum;
import uz.airline.uzairlinebookingsystem.enums.RoleEnum;

/**
 * DTO for {@link uz.airline.uzairlinebookingsystem.entity.UserEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

  private String firstname;
  private String lastname;
  // @gmail.com
  private String email;
  private String password;
  private GenderEnum gender;
}