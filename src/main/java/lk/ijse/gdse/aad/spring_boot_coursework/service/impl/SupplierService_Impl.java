package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.SupplierDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Customer;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Employee;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Supplier;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.NotFoundException;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.SupplierDao;
import lk.ijse.gdse.aad.spring_boot_coursework.service.SupplierService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class SupplierService_Impl implements SupplierService {
    private final SupplierDao supplierDao;

    private final Mapping mapping;

    @Override
    public SupplierDTO saveSupplier(SupplierDTO supplierDTO) {
//        supplierDTO.setCode(generateNextSupplierId());
//        System.out.println("==========================================================================================================================================="+supplierDTO);
//
//        return mapping.toSupplierDTO((supplierDao
//                .save(mapping.toSupplier(supplierDTO))));
        supplierDTO.setSupplierId(generateNextSupplierId());
        System.out.println("==========================================================================================================================================="+supplierDTO);

        Supplier supplier = mapping.toSupplier(supplierDTO);
        supplier.setSupplierId(supplierDTO.getSupplierId()); // Ensure supplierId is set

        return mapping.toSupplierDTO(supplierDao.save(supplier));
    }

    @Override
    public Iterable<SupplierDTO> getAllSuppliers() {
        return mapping.toSupplierDTOs(supplierDao.findAll());
    }

    @Override
    public SupplierDTO getSupplierById(String id) {
        return mapping.toSupplierDTO(supplierDao.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found")));
    }

    @Override
    public boolean updateSupplier(String supplierId, SupplierDTO supplierDTO) {
        Optional<Supplier> supplierOptional = supplierDao.findById(supplierId);
        if (!supplierOptional.isPresent()) throw new NotFoundException("Employee");
        {
            supplierOptional.get().setSupplierId(supplierId);
            supplierOptional.get().setSupplier_name(supplierDTO.getSupplier_name());
            supplierOptional.get().setCategory(supplierDTO.getCategory());
            supplierOptional.get().setAddress_line_01(supplierDTO.getAddress_line_01());
            supplierOptional.get().setAddress_line_02(supplierDTO.getAddress_line_02());
            supplierOptional.get().setAddress_line_03(supplierDTO.getAddress_line_03());
            supplierOptional.get().setAddress_line_04(supplierDTO.getAddress_line_04());
            supplierOptional.get().setAddress_line_05(supplierDTO.getAddress_line_05());
            supplierOptional.get().setContact_no_1(supplierDTO.getContact_no_1());
            supplierOptional.get().setContact_no_2(supplierDTO.getContact_no_2());
            supplierOptional.get().setEmail(supplierDTO.getEmail());
            supplierOptional.ifPresent(supplierDao::saveAndFlush);
        }
        return true;
    }

    @Override
    public boolean deleteSupplier(String id) {
        Optional<Supplier> supplierOptional = supplierDao.findById(id);
        if (!supplierOptional.isPresent()) throw new NotFoundException("Employee");{
            supplierOptional.ifPresent(supplierDao::delete);
            return true;
        }
    }

    @Override
    public String generateNextSupplierId() {
        Supplier lastSupplier = supplierDao.findFirstByOrderBySupplierIdDesc();
        if (lastSupplier == null) {
            return "Supp-001";
        }
        String lastCustomerId = lastSupplier.getSupplierId();
        int lastId = Integer.parseInt(lastCustomerId.split("-")[1]);
        int nextId = lastId + 1;
        return "Supp-" + String.format("%03d", nextId);
    }
}
