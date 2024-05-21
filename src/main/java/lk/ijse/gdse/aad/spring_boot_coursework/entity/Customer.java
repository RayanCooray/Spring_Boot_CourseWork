package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import jakarta.persistence.*;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Gender;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customer")
@Entity
@Data

public class Customer {
    @Id
    private String customerCode;
    private String customer_name;
    private String DOB;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String joined_date;
    private String total_points;
    @Enumerated(EnumType.STRING)
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
