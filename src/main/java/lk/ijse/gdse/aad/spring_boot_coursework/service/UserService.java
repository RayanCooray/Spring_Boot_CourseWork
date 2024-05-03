package lk.ijse.gdse.aad.spring_boot_coursework.service;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.UserDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService {
    UserDTO saveuser(UserDTO userDTOo);
    UserDetailsService USER_DETAILS_SERVICE();

    Collection<UserDTO> getAllUsers();

    boolean deleteUser(String userId);


    User getUserEntityById(String email);
}
