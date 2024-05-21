package lk.ijse.gdse.aad.spring_boot_coursework.service;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    boolean updateCustomer(String id, CustomerDTO customerDTO);

    boolean deleteCustomer(String id);

    Iterable<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomer(String id);

    String generateNextCustomerId();
}
