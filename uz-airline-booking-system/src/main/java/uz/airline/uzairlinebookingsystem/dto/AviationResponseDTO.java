package uz.airline.uzairlinebookingsystem.dto;

import java.util.List;
import lombok.Data;

@Data
public class AviationResponseDTO {
  private List<BookingDTO> data;
}

