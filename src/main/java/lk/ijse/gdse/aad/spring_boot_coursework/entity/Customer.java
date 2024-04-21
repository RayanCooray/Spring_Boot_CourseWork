package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Customer")
@Entity
public class Customer {
    @Id
    private String customer_code;
    private String customer_name;
    private String address;
    private Date DOB;
    private Gender gender;
    private String total_points;
    private String level;
    private Date joined_date;
}
