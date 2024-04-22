package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.UserDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Access_Role;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.User;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.UserDao;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.response.JWTAuthResponse;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.secure.SignIn;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.secure.SignUp;
import lk.ijse.gdse.aad.spring_boot_coursework.service.AuthenticationService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.JWTService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class Auth_Service implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private  final UserDao userDAO;
    private final Mapping mapping;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        // TODO: 2021-05-12
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getUser_email(), signIn.getPassword()));
        var userByEmail = userDAO.findByEmail(signIn.getUser_email()).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        System.out.println("==================="+userByEmail);
        String token = jwtService.generateToken(userByEmail);
        return JWTAuthResponse.builder().token(token).build();
    }

    @Override
    public JWTAuthResponse signUp(SignUp signUp) {
        UserDTO build = UserDTO.builder()
                .username_code(UUID.randomUUID().toString())
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
        return null;
    }
}

