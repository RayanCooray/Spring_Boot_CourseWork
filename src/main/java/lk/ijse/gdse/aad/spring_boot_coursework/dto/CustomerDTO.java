package lk.ijse.gdse.aad.spring_boot_coursework.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Gender;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Level;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

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
