package lk.ijse.gdse.aad.spring_boot_coursework.repo;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Customer;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface CustomerDao extends JpaRepository<Customer,String> {
    Customer findFirstByOrderByCustomerCodeDesc();
}
