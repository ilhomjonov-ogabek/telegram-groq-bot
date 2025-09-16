package homework3.exceptions;

public class RoomCountCantBeLessThanTenException extends RuntimeException {

  public RoomCountCantBeLessThanTenException(String message) {
    super(message);
  }
}
