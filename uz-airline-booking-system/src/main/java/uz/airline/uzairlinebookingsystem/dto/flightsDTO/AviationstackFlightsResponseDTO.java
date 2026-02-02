package uz.airline.uzairlinebookingsystem.dto.flightsDTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AviationstackFlightsResponseDTO(
    @JsonProperty("data") List<ApiFlightDTO> data
) {}

