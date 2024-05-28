package lk.ijse.gdse.aad.spring_boot_coursework.repo;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Customer;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface SupplierDao extends JpaRepository<Supplier,String> {
//    Supplier findFirstByOrderByCodeDesc();
    Supplier findFirstByOrderBySupplierIdDesc();
}
