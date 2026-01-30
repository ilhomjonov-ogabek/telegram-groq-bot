package org.example.springbootjpabuddy.project;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "project_column")
public class ProjectColumn {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;


  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "project_id")
  private Project project;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "order", unique = true)
  private Integer order;

}