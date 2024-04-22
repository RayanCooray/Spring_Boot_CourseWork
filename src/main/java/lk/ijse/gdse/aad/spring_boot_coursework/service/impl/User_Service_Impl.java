package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.UserDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.EmployeeDao;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.UserDao;
import lk.ijse.gdse.aad.spring_boot_coursework.service.UserService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class User_Service_Impl implements UserService {
    private final UserDao userDao;

    private final Mapping mapping;
    @Override
    public UserDTO saveuser(UserDTO userDTOo) {
        return mapping.toUserDTO(userDao
                .save(mapping.toUser(userDTOo)));
    }

    @Override
    public UserDetailsService USER_DETAILS_SERVICE() {
        return username ->
                userDao.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
