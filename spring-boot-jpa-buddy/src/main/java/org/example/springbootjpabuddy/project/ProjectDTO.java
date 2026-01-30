package org.example.springbootjpabuddy.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link Project}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO implements Serializable {

  private String name;
  private String code;
  private List<ProjectColumnDto> projectColumns = new ArrayList<>();
}