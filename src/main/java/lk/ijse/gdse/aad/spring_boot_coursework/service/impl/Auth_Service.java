package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.UserDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Access_Role;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.User;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.UserDao;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.response.JWTAuthResponse;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.secure.SignIn;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.secure.SignUp;
import lk.ijse.gdse.aad.spring_boot_coursework.service.AuthenticationService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.JWTService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class Auth_Service implements AuthenticationService {
    private static int counter = 0;

    private final PasswordEncoder passwordEncoder;
    private  final UserDao userDAO;
    private final Mapping mapping;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public static String generateID() {
        counter++;
        return String.format("USER%03d", counter);
    }

    @Override
    public JWTAuthResponse signIn(SignIn signInReq) {
        System.out.println(signInReq.getUser_email());
        // TODO: 2021-05-12
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInReq.getUser_email(), signInReq.getPassword()));
        var userByEmail = userDAO.findByEmail(signInReq.getUser_email()).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        System.out.println("==================="+userByEmail);
        String token = jwtService.generateToken(userByEmail);
        return JWTAuthResponse.builder().token(token).build();
    }

    @Override
    public JWTAuthResponse signUp(SignUp signUp) {
        UserDTO build = UserDTO.builder()
                .username_code(generateID())
                .email(signUp.getEmail())
                .Password(passwordEncoder.encode(signUp.getPassword()))
                .role(Access_Role.valueOf(String.valueOf(signUp.getRole())))
                .build();

        User user = userDAO.save(mapping.toUser(build));
        String generateToken = jwtService.generateToken(user);
        return JWTAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JWTAuthResponse refreshToken(String tokenAccess) {
        var userEntity = userDAO
                .findByEmail(jwtService.extractUsername(tokenAccess))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return JWTAuthResponse.builder().
                token(jwtService.generateToken(userEntity)).build();
    }
}

