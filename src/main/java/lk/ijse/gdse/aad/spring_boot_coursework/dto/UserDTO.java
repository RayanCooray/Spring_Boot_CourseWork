package lk.ijse.gdse.aad.spring_boot_coursework.dto;

import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Access_Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private String username_code;
    private String email;
    private String Password;
    private Access_Role role;
}
