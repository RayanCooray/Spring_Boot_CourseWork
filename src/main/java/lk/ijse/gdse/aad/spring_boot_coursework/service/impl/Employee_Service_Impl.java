package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import lk.ijse.gdse.aad.spring_boot_coursework.entity.Employee;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.NotFoundException;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.EmployeeDao;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.EmployeeDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.service.EmployeeService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class Employee_Service_Impl implements EmployeeService {
    private final EmployeeDao employeeDao;

    private final Mapping mapping;
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        return mapping.toCustomerDTO(employeeDao
                .save(mapping.toCustomer(employeeDTO)));
    }

    @Override
    public void updateEmployee(String id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeDao.findById(id);
        if (!employeeOptional.isPresent()) throw new NotFoundException("Employee");{
                    employeeOptional.get().setCode(id);
                    employeeOptional.get().setName(employeeDTO.getName());
                    employeeOptional.get().setGender(employeeDTO.getGender());
                    employeeOptional.get().setDate_of_birth(employeeDTO.getDate_of_birth());
                    employeeOptional.get().setContact_no(employeeDTO.getContact_no());
                    employeeOptional.get().setEmergency_contact_no(employeeDTO.getEmergency_contact_no());
                    employeeOptional.get().setProfile_picture(employeeDTO.getProfile_picture());
                    employeeOptional.get().setAccessRole(employeeDTO.getAccessRole());
                    employeeOptional.get().setDesignation(employeeDTO.getDesignation());
                    employeeOptional.get().setName_of_the_guardian(employeeDTO.getName_of_the_guardian());
                    employeeOptional.get().setDate_of_joining(employeeDTO.getDate_of_joining());
                    employeeOptional.get().setAddress(employeeDTO.getAddress());
                    employeeOptional.get().setAttached_branch(employeeDTO.getAttached_branch());
                    employeeOptional.get().setStatus(employeeDTO.getStatus());
                    employeeOptional.get().setEmail(employeeDTO.getEmail());
                    employeeOptional.ifPresent(employeeDao::saveAndFlush);
        }
    }
}
