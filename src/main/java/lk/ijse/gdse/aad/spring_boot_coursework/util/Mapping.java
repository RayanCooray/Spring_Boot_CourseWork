package lk.ijse.gdse.aad.spring_boot_coursework.util;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.EmployeeDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapping {
    private final ModelMapper mapper;
    public EmployeeDTO toCustomerDTO(Employee employee) {
        return  mapper.map(employee, EmployeeDTO.class);
    }
    public Employee toCustomer(EmployeeDTO employeeDTO) {
        return  mapper.map(employeeDTO, Employee.class);
    }
}
