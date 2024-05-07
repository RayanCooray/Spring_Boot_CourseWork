package lk.ijse.gdse.aad.spring_boot_coursework.util;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.*;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
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

    public ItemDTO toItemDTO(Item item) {
        return  mapper.map(item, ItemDTO.class);
    }

    public Item toItem(ItemDTO itemDTO) {
        return  mapper.map(itemDTO, Item.class);
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


    public Collection<UserDTO> toUserDTOs(List<User> all) {
        return mapper.map(all, Collection.class);
    }

    public MenWomenItem toGender(GenderDTO genderDTO) {
        return mapper.map(genderDTO, MenWomenItem.class);
    }

    public Occasion toOccasion(OccasionDTO occasionDTO) {
        return mapper.map(occasionDTO, Occasion.class);
    }

    public Variety toVariety(VarietyDTO varietyDTO) {
        return mapper.map(varietyDTO, Variety.class);
    }

    public GenderDTO[] toGenderDTOs(List<MenWomenItem> all) {
        return mapper.map(all, GenderDTO[].class);
    }

    public OccasionDTO[] toOccasionDTOs(List<Occasion> all) {
        return mapper.map(all, OccasionDTO[].class);
    }

    public VarietyDTO[] toVarietyDTOs(List<Variety> all) {
        return mapper.map(all, VarietyDTO[].class);
    }

    public GenderDTO toGenderDTO(MenWomenItem menWomenItem) {
        return mapper.map(menWomenItem, GenderDTO.class);
    }

    public OccasionDTO toOccasionDTO(Occasion occasion) {
        return mapper.map(occasion, OccasionDTO.class);
    }

    public VarietyDTO toVarietyDTO(Variety variety) {
        return mapper.map(variety, VarietyDTO.class);
    }

    public Item toItemEntity(ItemDTO itemDTO) {
        return mapper.map(itemDTO, Item.class);
    }

    public Iterable<ItemDTO> toItemDTOs(List<Item> all) {
        return mapper.map(all, List.class);
    }

}
