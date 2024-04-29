package lk.ijse.gdse.aad.spring_boot_coursework.dto;

import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Access_Role;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Gender;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO{
    private String code;
    private String name;
    private Gender gender;
    private String date_of_birth;
    private String contact_no;
    private String emergency_contact_no;
    private String profile_picture;
    private Access_Role accessRole;
    private String designation;
    private String name_of_the_guardian;
    private String date_of_joining;
    private String address;
    private String attached_branch;
    private Status status;
    private String email;
    private String username_code;
}
