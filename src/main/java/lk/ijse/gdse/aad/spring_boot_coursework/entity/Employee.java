package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import jakarta.persistence.Column;
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
@Table(name = "employee")
@Entity
public class Employee implements SuperEntity {
    @Id
    private String code;
    private String name;
    private Gender gender;
    private String date_of_birth;
    private String contact_no;
    private String emergency_contact_no;
    @Column(columnDefinition = "LONGTEXT")
    private String profile_picture;
    private Access_Role accessRole;
    private String designation;
    private String name_of_the_guardian;
    private String date_of_joining;
    private String address;
    private String attached_branch;
    private Status status;
    private String email;
}
