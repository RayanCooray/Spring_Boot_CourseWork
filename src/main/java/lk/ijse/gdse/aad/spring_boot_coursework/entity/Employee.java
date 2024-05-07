package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import jakarta.persistence.*;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Access_Role;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Branch;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Gender;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "employee")
@Entity
public class Employee implements SuperEntity {
    @Id
    private String code;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String date_of_birth;
    private String contact_no;
    private String emergency_contact_no;
    @Column(columnDefinition = "LONGTEXT")
    private String profile_picture;
    @Enumerated(EnumType.STRING)
    private Access_Role accessRole;
    private String designation;
    private String name_of_the_guardian;
    private String date_of_joining;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String postalCode;
    @Enumerated(EnumType.STRING)
    private Branch branch;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}