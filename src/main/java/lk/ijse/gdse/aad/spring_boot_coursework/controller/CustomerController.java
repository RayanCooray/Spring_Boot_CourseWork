package lk.ijse.gdse.aad.spring_boot_coursework.controller;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.CustomerDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Customer;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.CustomerDao;
import lk.ijse.gdse.aad.spring_boot_coursework.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
//@CrossOrigin(origins = "http://localhost:57160")
public class CustomerController {

//    private static int counter = 0;
    @Autowired
    private CustomerService customerService;
    @GetMapping("/health")
    public String healthTest(){
        return "Customer Health  Test";
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveCustomer(CustomerDTO customerDTO){
        System.out.println("========================================="+customerDTO.getCustomerCode());
        customerService.saveCustomer(customerDTO);
    }
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateCustomer(@RequestBody CustomerDTO customerDTO){
        System.out.println(customerDTO);
        return  customerService.updateCustomer(customerDTO.getCustomerCode(),customerDTO);

    }

    @DeleteMapping(value = "/{id}",produces = "application/json")
    public boolean deleteCustomer(@PathVariable ("id") String id){
        return customerService.deleteCustomer(id);
    }
    @GetMapping(value = "/{id}",produces = "application/json")
    public CustomerDTO getCustomer(@PathVariable ("id") String id){
        return customerService.getCustomer(id);
    }
    @GetMapping("/getAllCustomers")
    public Iterable<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }

}

