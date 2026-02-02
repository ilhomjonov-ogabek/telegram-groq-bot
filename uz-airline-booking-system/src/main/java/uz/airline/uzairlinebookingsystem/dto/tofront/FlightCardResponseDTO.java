package uz.airline.uzairlinebookingsystem.dto.tofront;

//Frontendga reyslar ro‘yxati uchun DTO
public record FlightCardResponseDTO(
    String id,            // tanlash uchun
    String flightIata,     // HY245
    String flightNumber,   // 245
    String airlineName,
    String fromIata,       // TAS
    String toIata,         // IST
    String departureTime,  // estimated yoki scheduled (String)
    String arrivalTime,
    String status          // scheduled/active...
) {}

