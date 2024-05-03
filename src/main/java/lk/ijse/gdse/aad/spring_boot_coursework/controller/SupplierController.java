package lk.ijse.gdse.aad.spring_boot_coursework.controller;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.SupplierDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/supplier")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class SupplierController {
    private static int counter = 0;
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/health")
    public String healthTest(){
        return "Health Test";
    }


    public static String generateID() {
        counter++;
        return String.format("SUPPLIER%03d", counter);
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void Savesupplier(SupplierDTO supplierDTO){
        supplierDTO.setSupplier_id(generateID());
        supplierService.saveSupplier(supplierDTO);
    }
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean updateSupplier(SupplierDTO supplierDTO){
        System.out.println(supplierDTO.getSupplier_id());
        return supplierService.updateSupplier(supplierDTO.getSupplier_id(),supplierDTO);
    }

    @GetMapping("/getAllSuppliers")
    public Iterable<SupplierDTO> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }


    @GetMapping("/getSupplierById")
    public SupplierDTO getSupplierById(@RequestPart ("id") String id){
        return supplierService.getSupplierById(id);
    }


    @DeleteMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean deleteSupplier(@RequestPart ("id") String id){
        return supplierService.deleteSupplier(id);
    }
}