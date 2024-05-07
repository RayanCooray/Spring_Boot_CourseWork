package lk.ijse.gdse.aad.spring_boot_coursework.service;

import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Branch;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO dto);

    void updateEmployee(String id, EmployeeDTO employeeDTO);

    void deleteEmployee(String id);

    EmployeeDTO getEmployee(String id);

    Iterable<EmployeeDTO> getAllEmployee();

    Iterable<EmployeeDTO> getAllEmployeesByBranch(Branch branch);
}
