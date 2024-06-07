package lk.ijse.gdse.aad.spring_boot_coursework.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class EmployeeDTO{
    private String code;
    @NotNull(message = "Employee name cannot be blank")
    private String name;
    @NotNull(message = "Employee gender cannot be blank")
    private Gender gender;
    @NotNull(message = "Employee dob cannot be blank")
    private String date_of_birth;
    @NotNull(message = "Employee Tel No cannot be blank")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid contact number format")
    private String contact_no;
    @NotNull(message = "Employee EM Tel No cannot be blank")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid contact number format")
    private String emergency_contact_no;
    private String profile_picture;
    @NotNull(message = "Employee Role cannot be blank")
    private Access_Role accessRole;
    @NotNull(message = "Employee Designation cannot be blank")
    private String designation;
    @NotNull(message = "Employee Guardian cannot be blank")
    private String name_of_the_guardian;
    @NotNull(message = "Employee Date of Joining cannot be blank")
    private String date_of_joining;
    @NotBlank(message = "Address 1 cannot be blank")
    private String address1;
    @NotBlank(message = "Address 2 cannot be blank")
    private String address2;
    @NotBlank(message = "Address 3 cannot be blank")
    private String address3;
    @NotBlank(message = "Address 4 cannot be blank")
    private String address4;
    @NotBlank(message = "Postal code cannot be blank")
    @Pattern(regexp = "\\d{5}", message = "Postal code must be 5 digits")
    private String postalCode;
    @NotNull
//    private String branchID;
    private Branch branch;
    @NotNull(message = "Employee Status cannot be blank")
    private Status status;
    @NotNull(message = "Employee Email cannot be blank")
    private String email;
}
