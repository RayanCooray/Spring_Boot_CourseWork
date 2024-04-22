package lk.ijse.gdse.aad.spring_boot_coursework.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.EmployeeDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.service.EmployeeService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Imp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class Employee {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/health")
    public String healthTest(){
        return "Health Test";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean savecustomer(EmployeeDTO employeeDTO, @RequestPart("profilepic") String profilepic){
        String dob = String.valueOf(employeeDTO.getDate_of_birth());
        employeeDTO.setDate_of_birth(dob);
        employeeDTO.setCode(UUID.randomUUID().toString());
        String dp = Imp.convertBase64(profilepic);
        employeeDTO.setProfile_picture(dp);
        employeeService.saveEmployee(employeeDTO);
        return true;
    }

    @PutMapping(value ="/{id}" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateEmployee(@PathVariable("id") String id,@RequestBody EmployeeDTO employeeDTO ,@RequestPart("profilepic") String profilepic){
        employeeDTO.setCode(id);
        String dob = String.valueOf(employeeDTO.getDate_of_birth());
        employeeDTO.setDate_of_birth(dob);
        employeeDTO.setCode(UUID.randomUUID().toString());
        String dp = Imp.convertBase64(profilepic);
        employeeDTO.setProfile_picture(dp);
        employeeService.updateEmployee(id, employeeDTO);
    }

}
