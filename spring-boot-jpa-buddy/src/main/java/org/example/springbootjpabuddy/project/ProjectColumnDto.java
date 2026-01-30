package org.example.springbootjpabuddy.project;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link ProjectColumn}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectColumnDto implements Serializable {

  private String name;
  private Integer order;
}