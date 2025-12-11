package uz.pdp.SpringBootDemoAplication;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TodoController {


  AtomicInteger counter = new AtomicInteger(1);


  List<Todo> todos = new ArrayList<>() {{
    add(new Todo(counter.getAndIncrement(), "Read a book about REST", "HIGH"));
    add(new Todo(counter.getAndIncrement(), "Read a book about API Server", "MEDIUM"));
  }};

  @GetMapping(value = "/todos", produces = "application/xml")
  @ResponseBody
  public List<Todo> getAll() {
    return todos;
  }

  @GetMapping(value = "/todos/{id}")
  @ResponseBody
  public Todo get(@PathVariable Integer id) {
    return todos.stream().filter(t -> Objects.equals(t.getId(), id)).findFirst()
        .orElseThrow(() -> new ItemNotFoundException("Todo not found"));
  }

  @PostMapping("/todos")
  @ResponseBody
  public Todo create(@RequestBody Todo todo) {
    todo.setId(counter.getAndIncrement());
    todos.add(todo);
    return todo;
  }

  @ExceptionHandler(ItemNotFoundException.class)
  public ResponseEntity<Object> error_404(ItemNotFoundException e, HttpServletRequest req) {
    return ResponseEntity
        .status(404)
        .body(Map.of(
            "error_message",e.getMessage(),
            "error_code",404,
            "error_path",req.getRequestURI(),
            "timestamp", LocalDateTime.now()
        ));
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  @Setter
  public static class Todo {

    private Integer id;
    private String title;
    private String priority;

  }

}
