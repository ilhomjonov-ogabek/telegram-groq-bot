package uz.airline.uzairlinebookingsystem.dto.flightsDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiAirportDTO(
    @JsonProperty("iata") String iata,          // TAS/IST
    String airport,                              // Airport name
    @JsonProperty("scheduled") String scheduled, // "2026-02-05T08:00:00+00:00"
    @JsonProperty("estimated") String estimated  // bo'lmasa null
) {}

