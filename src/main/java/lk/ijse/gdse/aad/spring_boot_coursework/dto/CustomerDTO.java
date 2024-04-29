package lk.ijse.gdse.aad.spring_boot_coursework.dto;

import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Gender;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Level;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerDTO {
    private String customer_code;
    private String customer_name;
    private String DOB;
    private Gender gender;
    private String joined_date;
    private String total_points;
    private Level level;
    private String address_line_01;
    private String address_line_02;
    private String address_line_03;
    private String address_line_04;
    private String address_line_05;
    private String contact;
    private String email;
    private String purchase_date_time;

}
