package lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.secure;


import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Access_Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignUp {
    private String password;
    private String email;
    private Access_Role role;
}
