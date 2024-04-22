package lk.ijse.gdse.aad.spring_boot_coursework.repo;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.UserDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);
}
