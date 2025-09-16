package homework3.exceptions;

public class PersonNameCantBeBlankException extends RuntimeException {

  public PersonNameCantBeBlankException(String message) {
    super(message);
  }
}
