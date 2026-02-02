package uz.airline.uzairlinebookingsystem.dto.flightsDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiAirlineDTO(
    @JsonProperty("iata") String iata,  // HY
    String name                         // Uzbekistan Airways
) {

}

