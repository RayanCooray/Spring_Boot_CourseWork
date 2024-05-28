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

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/health")
    public String healthTest(){
        return "Health Test";
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void Savesupplier(SupplierDTO supplierDTO){
        supplierService.saveSupplier(supplierDTO);
    }


    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean updateSupplier(SupplierDTO supplierDTO){
        System.out.println(supplierDTO.getSupplierId());
        return supplierService.updateSupplier(supplierDTO.getSupplierId(),supplierDTO);
    }

    @GetMapping("/getAllSuppliers")
    public Iterable<SupplierDTO> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }


    @GetMapping(value = "/{id}",produces = "application/json")
    public SupplierDTO getSupplierById(@PathVariable ("id") String id){
        return supplierService.getSupplierById(id);
    }


    @DeleteMapping(value = "/{id}",produces = "application/json")
    public boolean deleteSupplier(@PathVariable ("id") String id){
        return supplierService.deleteSupplier(id);
    }
}
