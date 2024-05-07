package lk.ijse.gdse.aad.spring_boot_coursework.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private String item_code;
    @NotNull
    private String item_desc;
    @NotNull
    private String item_pic;
    @NotNull
    private String status;
    private String genderCode;
    private String occasionCode;
    private String varietyCode;
}
