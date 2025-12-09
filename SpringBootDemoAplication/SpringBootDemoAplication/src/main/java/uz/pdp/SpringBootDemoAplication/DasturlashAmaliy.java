package uz.pdp.SpringBootDemoAplication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DasturlashAmaliy {

  @GetMapping("/massiv")
  public String massiv() {
    return "massiv";
  }

}
