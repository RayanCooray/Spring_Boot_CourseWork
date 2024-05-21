package lk.ijse.gdse.aad.spring_boot_coursework.repo;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Branch;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface EmployeeDao extends JpaRepository<Employee,String> {
    List<Employee> findByBranch(Branch attached_branch);

}
