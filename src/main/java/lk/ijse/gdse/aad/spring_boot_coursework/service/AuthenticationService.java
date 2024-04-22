package lk.ijse.gdse.aad.spring_boot_coursework.service;

import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.response.JWTAuthResponse;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.secure.SignIn;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.secure.SignUp;

public interface AuthenticationService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(SignUp signUp);
    JWTAuthResponse refreshToken(String tokenAccess);
}
