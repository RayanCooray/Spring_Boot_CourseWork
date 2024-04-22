package lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.secure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignIn {
    private String user_email;
    private String password;
}
