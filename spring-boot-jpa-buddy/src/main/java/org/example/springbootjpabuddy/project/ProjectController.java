package org.example.springbootjpabuddy.project;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

  private final ProjectRepository projectRepository;
  private final ProjectMapper projectMapper;

  public ProjectController(ProjectRepository projectRepository, ProjectMapper projectMapper) {
    this.projectRepository = projectRepository;
    this.projectMapper = projectMapper;
  }

  @GetMapping
  public List<ProjectDTO> getAllProject(){
    List<Project> all = projectRepository.findAll();
    return projectMapper.toDto(all);
  }

  @PostMapping
  public Project create(@RequestBody ProjectCreateDTO dto){
    Project project = projectMapper.fromCreateDTO(dto);
    project.setName(dto.getName());
    project.setCode(dto.getName().replace(" ", "_").toUpperCase());
    projectRepository.save(project);
    return project;
  }

}
