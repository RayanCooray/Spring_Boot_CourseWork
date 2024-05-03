package lk.ijse.gdse.aad.spring_boot_coursework.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OccasionDTO{
    @NotNull(message = "Occasion code should not null")
    private String occasionCode;
    @NotNull(message = "Occasion Description Should not null")
    private String occasionDesc;
}
