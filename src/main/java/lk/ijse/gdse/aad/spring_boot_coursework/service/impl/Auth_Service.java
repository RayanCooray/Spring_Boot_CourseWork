package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Status;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.EmployeeDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.UserDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Access_Role;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Employee;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.User;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.UserDao;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.response.JWTAuthResponse;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.secure.SignIn;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.secure.SignUp;
import lk.ijse.gdse.aad.spring_boot_coursework.service.AuthenticationService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.EmployeeService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.JWTService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Auth_Service implements AuthenticationService {
    private static int counter = 0;

    private final PasswordEncoder passwordEncoder;
    private  final UserDao userDAO;
    private final Mapping mapping;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmployeeService employeeService;

    public static String generateID() {
        counter++;
        return String.format("USER%03d", counter);
    }

    @Override
    public JWTAuthResponse signIn(SignIn signInReq) {
        System.out.println(signInReq.getEmail());
        // TODO: 2021-05-12
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInReq.getEmail(), signInReq.getPassword()));
        var userByEmail = userDAO.findByEmail(signInReq.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        System.out.println("==================="+userByEmail);
        System.out.println(userByEmail.getRole()+"==========================================================================");
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

//
        User user = userDAO.save(mapping.toUser(build));
        EmployeeDTO employeeDTO = new  EmployeeDTO();
        employeeDTO.setEmail(signUp.getEmail());
        employeeDTO.setAccessRole(signUp.getRole());
        employeeDTO.setStatus(Status.Single);
        employeeDTO.setDate_of_joining(String.valueOf(Date.valueOf(LocalDate.now())));
        employeeService.saveEmployee(employeeDTO);
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

