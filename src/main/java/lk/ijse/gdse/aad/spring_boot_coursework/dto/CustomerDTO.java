package lk.ijse.gdse.aad.spring_boot_coursework.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Gender;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Level;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerDTO {
    private String customerCode;
    @NotNull
    private String customer_name;
    @NotNull
    private String DOB;
    @NotNull
    private Gender gender;
    @NotNull
    private String joined_date;
    @NotNull
    private String total_points;
    @NotNull
    private Level level;
    @NotNull
    private String address_line_01;
    @NotNull
    private String address_line_02;
    @NotNull
    private String address_line_03;
    @NotNull
    private String address_line_04;
    @NotNull
    private String address_line_05;
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid contact number format")
    private String contact;
    @NotNull
    private String email;
    @NotNull
    private String purchase_date_time;

}
