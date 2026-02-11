package org.example.springbootjpabuddy.post;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link Post}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto implements Serializable {

  private Integer userId;
  private String title;
  private String body;
}