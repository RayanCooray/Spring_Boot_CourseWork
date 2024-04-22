package lk.ijse.gdse.aad.spring_boot_coursework.service;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDTO saveuser(UserDTO userDTOo);

    UserDetailsService USER_DETAILS_SERVICE();
}
