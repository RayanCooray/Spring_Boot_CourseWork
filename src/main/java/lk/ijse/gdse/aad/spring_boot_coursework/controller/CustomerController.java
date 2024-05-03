package lk.ijse.gdse.aad.spring_boot_coursework.controller;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.CustomerDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class CustomerController {
    private static int counter = 0;
    @Autowired
    private CustomerService customerService;
    @GetMapping("/health")
    public String healthTest(){
        return "Customer Health  Test";
    }

    public static String generateID() {
        counter++;
        return String.format("CUSTOMER%03d", counter);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveCustomer(CustomerDTO customerDTO){
        System.out.println(customerDTO.getDOB());
        customerDTO.setCustomer_code(generateID());
        customerService.saveCustomer(customerDTO);
    }
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateCustomer(@RequestBody CustomerDTO customerDTO){
        return  customerService.updateCustomer(customerDTO.getCustomer_code(),customerDTO);

    }

    @DeleteMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean deleteCustomer(@RequestPart ("id") String id){
        return customerService.deleteCustomer(id);
    }
    @GetMapping(value = "/getCustomerById",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CustomerDTO getCustomer(@RequestPart("id") String id){
        return customerService.getCustomer(id);
    }
    @GetMapping("/getAllCustomers")
    public Iterable<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }
}
