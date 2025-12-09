package uz.pdp.SpringBootDemoAplication;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
  private String title;
  private String description;
  private Double price;
  private String author;

}
