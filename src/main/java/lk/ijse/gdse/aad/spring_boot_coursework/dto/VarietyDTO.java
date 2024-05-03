package lk.ijse.gdse.aad.spring_boot_coursework.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VarietyDTO {
    @NotNull(message = "Variety Code Should Be not null.")
    private String varietyCode;
    @NotNull(message = "Variety Description Should Be not null.")
    private String varietyDesc;
}
