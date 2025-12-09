package uz.pdp.SpringBootDemoAplication;




public record BookCreateDTO (
   String title,
   String description,
   Double price,
   String author
){}
