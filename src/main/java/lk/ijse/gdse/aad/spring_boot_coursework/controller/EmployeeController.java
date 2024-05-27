package lk.ijse.gdse.aad.spring_boot_coursework.controller;

import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Access_Role;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Branch;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Gender;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Status;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.EmployeeDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.service.EmployeeService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Imp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
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
    public boolean saveemp(@Validated EmployeeDTO employeeDTO,@RequestPart("profile_pictrue") String profilePicture ){
        String dob = String.valueOf(employeeDTO.getDate_of_birth());
        employeeDTO.setDate_of_birth(dob);
        System.out.println(employeeDTO.getCode());
        employeeService.saveEmployee(employeeDTO);
        return true;
    }


//    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public void updateEmployee(@RequestPart ("code") String code, @RequestPart ("name")
//    String name, @RequestPart ("Gender") String gender, @RequestPart ("date_of_birth") String dateOfBirth,
//                               @RequestPart("contact_no") String contactNo, @RequestPart("emergency_contact_no") String emergency_contact_no,
//                               @RequestPart("profile_picture") MultipartFile profile_picture, @RequestPart("accessRole") String access_role,
//                               @RequestPart("designation") String designation, @RequestPart("name_of_the_guardian") String name_of_the_guardian,
//                               @RequestPart("date_of_joining")String date_of_joining,@RequestPart("address1") String address1,
//                               @RequestPart("address2") String address2, @RequestPart("address3") String address3, @RequestPart("address4") String address4,
//                               @RequestPart("postalCode") String postalCode, @RequestPart("branch") String branch, @RequestPart("status") String status,@RequestPart("email") String email )
//    {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        employeeDTO.setCode(code);
//        employeeDTO.setName(name);
//        employeeDTO.setGender(Gender.valueOf(gender));
//        employeeDTO.setDate_of_birth(dateOfBirth);
//        employeeDTO.setContact_no(contactNo);
//        employeeDTO.setEmergency_contact_no(emergency_contact_no);
//        // Handle profile picture if present
//        if (profile_picture != null && !profile_picture.isEmpty()) {
//            try {
//                byte[] bytes = profile_picture.getBytes();
//                String base64String = Base64.getEncoder().encodeToString(bytes);
//                String dp = Imp.convertBase64(base64String);
//                employeeDTO.setProfile_picture(dp);
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            }
//        }
//
//        employeeDTO.setAccessRole(Access_Role.valueOf(access_role));
//        employeeDTO.setDesignation(designation);
//        employeeDTO.setName_of_the_guardian(name_of_the_guardian);
//        employeeDTO.setDate_of_joining(date_of_joining);
//        employeeDTO.setAddress1(address1);
//        employeeDTO.setAddress2(address2);
//        employeeDTO.setAddress3(address3);
//        employeeDTO.setAddress4(address4);
//        employeeDTO.setPostalCode(postalCode);
//        employeeDTO.setBranch(String.valueOf(Branch.valueOf(branch)));
//        employeeDTO.setStatus(Status.valueOf(status));
//        employeeDTO.setEmail(email);
//        employeeService.updateEmployee(employeeDTO);
//
//
//    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.updateEmployee(employeeDTO);
    }

    @DeleteMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void deleteEmployee(@RequestPart("id") String id){
        employeeService.deleteEmployee(id);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public EmployeeDTO getEmployee(@PathVariable("id") String id){
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
