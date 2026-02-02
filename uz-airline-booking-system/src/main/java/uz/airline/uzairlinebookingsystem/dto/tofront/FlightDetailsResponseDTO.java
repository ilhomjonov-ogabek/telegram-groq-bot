package uz.airline.uzairlinebookingsystem.dto.tofront;

public record FlightDetailsResponseDTO(
    String id,
    String flightIata,
    String flightNumber,
    String airlineIata,
    String airlineName,

    String fromIata,
    String toIata,
    String fromAirportName,
    String toAirportName,

    String scheduledDeparture,
    String estimatedDeparture,
    String scheduledArrival,
    String estimatedArrival,

    String status
) {}
