package uz.airline.uzairlinebookingsystem.dto.apidto;

import lombok.Data;

@Data
public class AviationFlightDTO {

  private Flight flight;
  private Airline airline;
  private AirportSide departure;
  private AirportSide arrival;

  @Data
  public static class Flight {
    private String iata;
  }

  @Data
  public static class Airline {
    private String name;
  }

  @Data
  public static class AirportSide {
    private String iata;
    private String scheduled;
  }
}

