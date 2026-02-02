package uz.airline.uzairlinebookingsystem.dto.flightsDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiFlightInfoDTO(
    @JsonProperty("iata") String iata,  // HY245
    String number                       // 245
) {

}

