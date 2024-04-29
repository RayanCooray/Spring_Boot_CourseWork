package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.CustomerDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Customer;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.NotFoundException;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.CustomerDao;
import lk.ijse.gdse.aad.spring_boot_coursework.service.AuthenticationService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.CustomerService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class Customer_Service_Impl implements CustomerService {
    private final CustomerDao customerDao;



    private final Mapping mapping;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return mapping.toCustomerDTO((customerDao
                .save(mapping.toCustomer(customerDTO))));
    }

    @Override
    public boolean updateCustomer(String id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerDao.findById(id);
        if (!customerOptional.isPresent()) throw new NotFoundException("Employee");{
            customerOptional.get().setCustomer_code(id);
            customerOptional.get().setCustomer_name(customerDTO.getCustomer_name());
            customerOptional.get().setGender(customerDTO.getGender());
            customerOptional.get().setDOB(customerDTO.getDOB());
            customerOptional.get().setGender(customerDTO.getGender());
            customerOptional.get().setJoined_date(customerDTO.getJoined_date());
            customerOptional.get().setTotal_points(Integer.valueOf(customerDTO.getTotal_points()));
            customerOptional.get().setLevel(customerDTO.getLevel());
            customerOptional.get().setAddress_line_01(customerDTO.getAddress_line_01());
            customerOptional.get().setAddress_line_02(customerDTO.getAddress_line_02());
            customerOptional.get().setAddress_line_03(customerDTO.getAddress_line_03());
            customerOptional.get().setAddress_line_04(customerDTO.getAddress_line_04());
            customerOptional.get().setAddress_line_05(customerDTO.getAddress_line_05());
            customerOptional.get().setContact(customerDTO.getContact());
            customerOptional.get().setEmail(customerDTO.getEmail());
            customerOptional.get().setPurchase_date_time(customerDTO.getPurchase_date_time());
            customerOptional.ifPresent(customerDao::saveAndFlush);
            return true;
        }
    }

    @Override
    public boolean deleteCustomer(String id) {
        Optional<Customer> customerOptional = customerDao.findById(id);
        if (!customerOptional.isPresent()) throw new NotFoundException("Employee");{
            customerOptional.ifPresent(customerDao::delete);
            return true;
        }
    }

    @Override
    public Iterable<CustomerDTO> getAllCustomers() {
        return mapping.toCustomerDTOs(customerDao.findAll());
    }

    @Override
    public CustomerDTO getCustomer(String id) {
        Optional<Customer> customerOptional = customerDao.findById(id);
        if (!customerOptional.isPresent()) throw new NotFoundException("Employee");{
            return mapping.toCustomerDTO(customerOptional.get());
        }
    }


}
