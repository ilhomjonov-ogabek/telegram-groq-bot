package uz.airline.uzairlinebookingsystem.dto.flightsDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiFlightDTO(
    @JsonProperty("flight_status") String flightStatus,
    ApiAirportDTO departure,
    ApiAirportDTO arrival,
    ApiAirlineDTO airline,
    ApiFlightInfoDTO flight
) {}


