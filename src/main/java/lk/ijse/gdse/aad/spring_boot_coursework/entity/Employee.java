package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import jakarta.persistence.*;
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
    private String address;
    private String attached_branch;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String email;
    @OneToOne
    @JoinColumn(name = "user_email")
    private User user;
}