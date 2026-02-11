package spring.boot.springadvanced.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link spring.boot.springadvanced.entity.Post}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateDTO implements Serializable {

  private Integer id;
  private String title;
  private String body;
}