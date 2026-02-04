package uz.airline.uzairlinebookingsystem.dto.apidto;

import java.util.List;
import lombok.Data;

@Data
public class AviationStackResponseDTO {
  private List<AviationFlightDTO> data;
}

