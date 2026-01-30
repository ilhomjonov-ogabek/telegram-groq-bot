package org.example.springbootjpabuddy.project;

import java.util.Optional;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {

  @Query("select p from Project p where upper(p.name) = upper(?1)")
  Optional<Project> findByNameIgnoreCaseAllIgnoreCase(String name);

  Optional<Project> getByCodeAllIgnoreCase(String code);

  @Query("select p from Project p where upper(p.name) = upper(?1) and upper(p.code) = upper(?2)")
  GeoResult<Project> getByNameAndCode(String name, String code);
}