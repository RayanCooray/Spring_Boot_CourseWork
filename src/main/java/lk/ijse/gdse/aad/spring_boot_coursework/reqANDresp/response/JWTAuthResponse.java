package lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTAuthResponse {
    private String token;
}
