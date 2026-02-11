package spring.boot.springadvanced.commands;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.constraints.Positive;
import java.util.Arrays;
import java.util.Set;
import java.util.StringJoiner;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.shell.command.annotation.ExceptionResolver;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import spring.boot.springadvanced.annotation.NonZero;
import spring.boot.springadvanced.service.CalculatorService;

@ShellComponent
@RequiredArgsConstructor
public class CalculatorCommand {

  private final CalculatorService calculatorService;
  private Boolean loggedIn = false;

  @ShellMethod(value = "Add method command")
  public double add(double a, double b) {
    return calculatorService.add(a, b);
  }

  @ShellMethod
  public double sub(double a, @Positive double b) {
    return calculatorService.sub(a, b);
  }

  @ShellMethod
  public double mul(double a, double b) {
    return calculatorService.mul(a, b);
  }

  @ShellMethod
/*
  @ShellMethodAvailability("availabilityForLogin")
*/
  public double div(double a, @NonZero double b) {
    return calculatorService.div(a, b);
  }

  @ShellMethod(value = "Sum of array", key = "sum")
  public double sum(@ShellOption(arity = -1) double[] nums) {
    return Arrays.stream(nums).sum();
  }

  @ShellMethodAvailability({"div", "add", "mul", "div","logout"})
  public Availability availabilityForLogin() {
    return loggedIn ? Availability.available() : Availability.unavailable("=> Login First");
  }

  @ShellMethodAvailability({"login"})
  public Availability availabilityForLogout() {
    return loggedIn ? Availability.unavailable("=> Logout First") : Availability.available();
  }

  @ShellMethod
  public String login(@ShellOption(value = "-u") String username,
      @ShellOption(value = "-p") String password) {
    // check db
    loggedIn = true;
    return "Successfully sign in";
  }

  @ShellMethod
  public String logout() {
    // check db
    loggedIn = false;
    return "Successfully log out";
  }


  @ExceptionResolver({ParameterValidationException.class})
  CommandHandlingResult errorHandler(ParameterValidationException e) {
    Set<ConstraintViolation<Object>> constraintViolations = e.getConstraintViolations();
    StringJoiner joiner = new StringJoiner("\n", "", "\n");
    constraintViolations.forEach(constraintViolation -> {
      joiner.add(constraintViolation.getMessage() + " : " + constraintViolation.getPropertyPath()
          .toString());
    });
    return CommandHandlingResult.of(joiner.toString(), 400);
  }


}
