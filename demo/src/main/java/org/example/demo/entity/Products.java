package org.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "products")
public class Products {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @Column(nullable = false)
  private String name;

  @Column
  private String information;

  @Column(nullable = false)
  private Double price;

  @Column
  private int count;

  @Column
  private int userCount = 0;

  @Column(nullable = false)
  private Long categoryId;

  @Column
  private Long userId;

  @Column
  private boolean active;

  @Column
  private String imageId;


}
