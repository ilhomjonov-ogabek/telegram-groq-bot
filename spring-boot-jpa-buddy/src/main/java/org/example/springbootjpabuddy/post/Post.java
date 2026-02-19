package org.example.springbootjpabuddy.post;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {

  @Id
  private Integer id;
  private Integer userId;
  private String title;
  private String body;
}
