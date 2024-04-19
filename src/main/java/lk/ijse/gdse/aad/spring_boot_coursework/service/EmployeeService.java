package lk.ijse.gdse.aad.spring_boot_coursework.service;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO dto);


    void updateEmployee(String id, EmployeeDTO employeeDTO);
}
