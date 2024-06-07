package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Branch;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.BranchEntity;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Employee;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.User;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.NotFoundException;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.BranchDAO;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.EmployeeDao;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.EmployeeDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.UserDao;
import lk.ijse.gdse.aad.spring_boot_coursework.service.AuthenticationService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.EmployeeService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class Employee_Service_Impl implements EmployeeService {
    private final EmployeeDao employeeDao;
    private final UserDao userDao;
    private final BranchDAO branchDAO;
    private final Mapping mapping;
    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        Employee employeeEntity = mapping.toEmployee(employeeDTO);
        employeeEntity.setCode(generateNextEMPId());
        System.out.println("============================================================"+employeeDTO.getCode());
        String email = employeeDTO.getEmail();
        System.out.println(email);

        Optional<User> optionalUser = userDao.findByEmail(email);
        System.out.println(optionalUser);
        User user = new User();
        System.out.println(optionalUser.get().getUsername_code());
        user.setUsername_code(optionalUser.get().getUsername_code());
        user.setEmail(email);
        user.setPassword(optionalUser.get().getPassword());
        user.setRole(optionalUser.get().getRole());
        employeeEntity.setUser(user);
        mapping.toEmployeeDTO(employeeDao.save(employeeEntity));
    }


    @Override
    public void deleteEmployee(String id) {
        Optional<Employee> employeeOptional = employeeDao.findById(id);
        if (!employeeOptional.isPresent()) throw new NotFoundException("Employee deleted"+id);{
            employeeOptional.ifPresent(employeeDao::delete);
        }
    }

    @Override
    public EmployeeDTO getEmployee(String id) {
        Optional<Employee> employeeOptional = employeeDao.findById(id);
        if (!employeeOptional.isPresent()) throw new NotFoundException("Employee");{
            System.out.println(employeeOptional.get().getName());
            System.out.println(employeeOptional.get().getEmail());
            return mapping.toEmployeeDTO(employeeOptional.get());
        }
    }

    @Override
    public Iterable<EmployeeDTO> getAllEmployee() {
        List<Employee> employees = employeeDao.findAll();
        Iterable<EmployeeDTO> employeeDTOs = mapping.toEmployeeDTOs(employees);
//        System.out.println(employeeDTOs);
        return employeeDTOs;
    }

    @Override
    public Iterable<EmployeeDTO> getAllEmployeesByBranch(Branch branch) {
        System.out.println(branch);
        return mapping.toEmployeeDTOs(employeeDao.findByBranch(branch));
    }



    @Override
    public String generateNextEMPId() {
        Employee employee = employeeDao.findFirstByOrderByCodeDesc();
        if (employee == null) {
            return "Emp-001";
        }
        String lastCustomerId = employee.getCode();
        int lastId = Integer.parseInt(lastCustomerId.split("-")[1]);
        int nextId = lastId + 1;
        return "Emp-" + String.format("%03d", nextId);
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeDao.findById(employeeDTO.getCode());
        if (!employeeOptional.isPresent()) throw new NotFoundException("Employee");{
            employeeOptional.get().setCode(employeeDTO.getCode());
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
            employeeOptional.get().setAddress1(employeeDTO.getAddress1());
            employeeOptional.get().setAddress2(employeeDTO.getAddress2());
            employeeOptional.get().setAddress3(employeeDTO.getAddress3());
            employeeOptional.get().setAddress4(employeeDTO.getAddress4());
            employeeOptional.get().setPostalCode(employeeDTO.getPostalCode());
            employeeOptional.get().setBranch(employeeDTO.getBranch());
//            Optional<BranchEntity> optional = branchDAO.findById(employeeDTO.getBranchID());
//            System.out.println(optional);
//            employeeOptional.get().setBranch(optional.get());
            employeeOptional.get().setStatus(employeeDTO.getStatus());
            employeeOptional.get().setEmail(employeeDTO.getEmail());
            Optional<User> optionalUser = userDao.findByEmail(employeeDTO.getEmail());
            System.out.println(optionalUser);
            User user = new User();
            System.out.println(optionalUser.get().getUsername_code());
            user.setUsername_code(optionalUser.get().getUsername_code());
            user.setEmail(employeeDTO.getEmail());
            user.setPassword(optionalUser.get().getPassword());
            user.setRole(optionalUser.get().getRole());
            employeeOptional.get().setUser(user);
            employeeOptional.ifPresent(employeeDao::saveAndFlush);
        }
    }


}
