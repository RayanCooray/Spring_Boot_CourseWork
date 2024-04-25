package lk.ijse.gdse.aad.spring_boot_coursework.util;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.CustomerDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.EmployeeDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.SupplierDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.UserDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Customer;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Employee;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Supplier;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapping {
    private final ModelMapper mapper;
    public EmployeeDTO toEmployeeDTO(Employee employee) {
        return  mapper.map(employee, EmployeeDTO.class);
    }
    public Employee toEmployee(EmployeeDTO employeeDTO) {
        return  mapper.map(employeeDTO, Employee.class);
    }

    public UserDTO toUserDTO(User user) {
        return  mapper.map(user, UserDTO.class);
    }

    public User toUser(UserDTO userDTO) {
        return  mapper.map(userDTO, User.class);
    }

    public CustomerDTO toCustomerDTO(Customer customer) {
        return  mapper.map(customer, CustomerDTO.class);
    }

    public Customer toCustomer(CustomerDTO customerDTO) {
        return  mapper.map(customerDTO, Customer.class);
    }

    public SupplierDTO toSupplierDTO(Supplier supplier) {
        return  mapper.map(supplier, SupplierDTO.class);
    }

    public Supplier toSupplier(SupplierDTO supplierDTO) {
        return  mapper.map(supplierDTO, Supplier.class);
    }

    public Iterable<CustomerDTO> toCustomerDTOs(List<Customer> all) {
        return mapper.map(all, List.class);
    }

    public Iterable<EmployeeDTO> toEmployeeDTOs(List<Employee> all) {
        return mapper.map(all, List.class);
    }

    public Iterable<SupplierDTO> toSupplierDTOs(List<Supplier> all) {
        return mapper.map(all, List.class);
    }
}
