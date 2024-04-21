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
@RequestMapping("employee")
@RequiredArgsConstructor
//@MultipartConfig(
//        location = "F:\\AAD Coursework\\Spring_Boot_CourseWork\\src\\main\\resources\\temp",
//        maxFileSize = 1024 * 1024 * 10,
//        maxRequestSize = 1024 * 1024 * 50,
//        fileSizeThreshold = 1024 * 1024 * 5
//)
public class Employee {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/health")
    public String healthTest(){
        return "Health Test";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void savecustomer(EmployeeDTO employeeDTO, @RequestPart("profilepic") String profilepic){
        String dob = String.valueOf(employeeDTO.getDate_of_birth());
        employeeDTO.setDate_of_birth(dob);
        employeeDTO.setCode(UUID.randomUUID().toString());
        String dp = Imp.convertBase64(profilepic);
        employeeDTO.setProfile_picture(dp);
        employeeService.saveEmployee(employeeDTO);

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
