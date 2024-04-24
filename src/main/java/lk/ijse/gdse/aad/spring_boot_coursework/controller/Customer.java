package lk.ijse.gdse.aad.spring_boot_coursework.controller;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.CustomerDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class Customer {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/health")
    public String healthTest(){
        return "Customer Health  Test";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveCustomer(CustomerDTO customerDTO){
        System.out.println(customerDTO.getDOB());
        customerDTO.setCustomer_code(UUID.randomUUID().toString());
        customerService.saveCustomer(customerDTO);
    }
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateCustomer(@RequestBody CustomerDTO customerDTO){
        return  customerService.updateCustomer(customerDTO.getCustomer_code(),customerDTO);

    }
}
