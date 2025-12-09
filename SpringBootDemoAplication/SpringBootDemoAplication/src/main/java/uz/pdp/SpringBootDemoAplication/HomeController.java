package uz.pdp.SpringBootDemoAplication;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

  private final MailConfigs mailConfigs;
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public HomeController(MailConfigs mailConfigs, JdbcTemplate jdbcTemplate) {
    this.mailConfigs = mailConfigs;
    this.jdbcTemplate = jdbcTemplate;
  }


  @Value("${welcome.message}")
  private String message;

  @GetMapping("/")
  public String homePage(Model model) {
    model.addAttribute("message", message);
    String sql = "select * from book";
    List<Book> books = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Book.class));
    model.addAttribute("books", books);

    return "home";
  }

  @GetMapping("/book/create")
  public String bookCreatePage() {
    return "book_create";
  }

  @PostMapping("/book/create")
  public String bookCreate(@ModelAttribute BookCreateDTO dto) {
    String sql = "INSERT INTO book ( title,description,price,author) VALUES ( ?, ?, ?, ?)";
    jdbcTemplate.update(sql, dto.title(), dto.description(), dto.price(), dto.author());
    return "redirect:/";
  }

  @GetMapping("/book/search")
  public String bookSearchPage() {
    return "search_book";
  }


  @PostMapping("/search")
  public String bookSearch(@ModelAttribute SearchDTO dto, Model model) {

    String sql = "SELECT * FROM book b WHERE b.price = ? OR b.title LIKE ?";

    List<Book> searchedBooks = jdbcTemplate.query(
        sql,
        BeanPropertyRowMapper.newInstance(Book.class),
        dto.price(),
        "%" + dto.title() + "%"
    );

    model.addAttribute("searched_books", searchedBooks);
    return "searched_books";
  }









}
