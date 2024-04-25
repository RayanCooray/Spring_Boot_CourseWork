package lk.ijse.gdse.aad.spring_boot_coursework.service;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.SupplierDTO;

public interface SupplierService {
    SupplierDTO saveSupplier(SupplierDTO supplierDTO);

    Iterable<SupplierDTO> getAllSuppliers();

    SupplierDTO getSupplierById(String id);

    boolean updateSupplier(String supplierId, SupplierDTO supplierDTO);
}
