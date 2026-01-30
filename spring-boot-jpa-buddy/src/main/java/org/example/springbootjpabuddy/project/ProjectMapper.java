package org.example.springbootjpabuddy.project;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface ProjectMapper {

  Project toEntity(ProjectDTO projectDTO);

  ProjectDTO toDto(Project project);

  List<ProjectDTO> toDto(List<Project> project);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Project partialUpdate(ProjectDTO projectDTO, @MappingTarget Project project);

  Project fromCreateDTO(ProjectCreateDTO projectCreateDTO);

  ProjectCreateDTO toDto1(Project project);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Project partialUpdate(
      ProjectCreateDTO projectCreateDTO, @MappingTarget Project project);
}