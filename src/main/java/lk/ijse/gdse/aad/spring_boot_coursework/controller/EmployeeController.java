package lk.ijse.gdse.aad.spring_boot_coursework.controller;

import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Branch;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.EmployeeDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.service.EmployeeService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Imp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/health")
    public String healthTest(){
        return "Health Test";
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean saveemp(@Validated EmployeeDTO employeeDTO){
        String dob = String.valueOf(employeeDTO.getDate_of_birth());
        employeeDTO.setDate_of_birth(dob);
        System.out.println(employeeDTO.getCode());
        employeeService.saveEmployee(employeeDTO);
        return true;
    }


    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateEmployee(@RequestPart("id") String id,@RequestBody EmployeeDTO employeeDTO ,@RequestPart("profilepic") String profilepic){
        employeeDTO.setCode(id);
        String dob = String.valueOf(employeeDTO.getDate_of_birth());
        employeeDTO.setDate_of_birth(dob);
        String dp = Imp.convertBase64(profilepic);
        System.out.println("===================================================================================="+id);
        employeeDTO.setProfile_picture(dp);
        employeeService.updateEmployee(id, employeeDTO);
    }
    @DeleteMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void deleteEmployee(@RequestPart("id") String id){
        employeeService.deleteEmployee(id);
    }

    @GetMapping(value = "/getEmployeeById",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public EmployeeDTO getEmployee(@RequestPart("id") String id){
        return employeeService.getEmployee(id);
    }

    @GetMapping(value = "/getEmployees")
    public Iterable<EmployeeDTO> getAllEmployee(){
        System.out.println(".........................................");
        return employeeService.getAllEmployee();
    }


    @GetMapping(value = "/getAllEmployeesByBranch/{branch}")
    public Iterable<EmployeeDTO> getAllEmployeesByBranch(@PathVariable("branch") Branch branch) {
        System.out.println("=======================" + branch);
        return employeeService.getAllEmployeesByBranch(branch);
    }


}
