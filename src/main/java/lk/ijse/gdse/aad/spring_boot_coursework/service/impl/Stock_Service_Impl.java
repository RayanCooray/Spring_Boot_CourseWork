package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Branch;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.StockDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.*;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.*;
import lk.ijse.gdse.aad.spring_boot_coursework.service.StockService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class Stock_Service_Impl implements StockService {

    private final EmployeeDao employeeDao;
    private final ItemDao itemDao;
    private final SupplierDao supplierDao;
    private final StockDAO stockDAO;
    private final BranchDAO branchDAO;
    private final Mapping mapping;

//    @Override
//    public void saveStock(StockDTO stockDTO,String email) {
//        stockDTO.setStockId(generateNextStockId());
//        Stock stockEntity = mapping.toStockEntity(stockDTO);
//
//        Employee employeeDaoByEmail = employeeDao.findByEmail(email);
//        Optional<BranchEntity> branch = branchDAO.findById(employeeDaoByEmail.getBranch().getBranchId());
//        if (branch.isPresent()) {
//            BranchEntity branchEntity = branch.get();
//            stockEntity.setBranchEntity(branchEntity);
//        }
//        stockEntity.setStockId(stockDTO.getStockId());
//        stockEntity.setQty(stockDTO.getQty());
//        stockEntity.setUnitBuyingPrice(stockDTO.getUnitBuyingPrice());
//        stockEntity.setUnitSellingPrice(stockDTO.getUnitSellingPrice());
//        Optional<Supplier> supplierEntityOptional = supplierDao.findById(stockDTO.getSupplierEntity());
//        if (supplierEntityOptional.isPresent()) {
//            Supplier supplierEntity = supplierEntityOptional.get();
//            stockEntity.setSupplierEntity(supplierEntity);
//        } else {
//            throw new RuntimeException("Supplier not found with ID: " + stockDTO.getSupplierEntity());
//        }
//
//
//        Optional<Item> itemEntityOptional = itemDao.findById(stockDTO.getItemEntity());
//        if (itemEntityOptional.isPresent()) {
//            Item itemEntity = itemEntityOptional.get();
//            stockEntity.setItemEntity(itemEntity);
//        } else {
//            throw new RuntimeException("Item not found with ID: " + stockDTO.getItemEntity());
//        }
//
//
//
//        stockDAO.save(stockEntity);
//    }

    @Override
    public void saveStock(StockDTO stockDTO) {
        stockDTO.setStockId(generateNextStockId());
        Stock stockEntity = mapping.toStockEntity(stockDTO);


        stockEntity.setStockId(stockDTO.getStockId());
        stockEntity.setQty(stockDTO.getQty());
        stockEntity.setUnitBuyingPrice(stockDTO.getUnitBuyingPrice());
        stockEntity.setUnitSellingPrice(stockDTO.getUnitSellingPrice());
        Optional<Supplier> supplierEntityOptional = supplierDao.findById(stockDTO.getSupplierEntity());
        if (supplierEntityOptional.isPresent()) {
            Supplier supplierEntity = supplierEntityOptional.get();
            stockEntity.setSupplierEntity(supplierEntity);
        } else {
            throw new RuntimeException("Supplier not found with ID: " + stockDTO.getSupplierEntity());
        }


        Optional<Item> itemEntityOptional = itemDao.findById(stockDTO.getItemEntity());
        if (itemEntityOptional.isPresent()) {
            Item itemEntity = itemEntityOptional.get();
            stockEntity.setItemEntity(itemEntity);
        } else {
            throw new RuntimeException("Item not found with ID: " + stockDTO.getItemEntity());
        }



        stockDAO.save(stockEntity);
    }

    @Override
    public void updateStock(StockDTO stockDTO) {
        Stock stockEntity = mapping.toStockEntity(stockDTO);
        stockEntity.setStockId(stockDTO.getStockId());
        stockEntity.setQty(stockDTO.getQty());
        stockEntity.setUnitBuyingPrice(stockDTO.getUnitBuyingPrice());
        stockEntity.setUnitSellingPrice(stockDTO.getUnitSellingPrice());
        Optional<Supplier> supplierEntityOptional = supplierDao.findById(stockDTO.getSupplierEntity());
        if (supplierEntityOptional.isPresent()) {
            Supplier supplierEntity = supplierEntityOptional.get();
            stockEntity.setSupplierEntity(supplierEntity);
        } else {
            throw new RuntimeException("Supplier not found with ID: " + stockDTO.getSupplierEntity());
        }


        Optional<Item> itemEntityOptional = itemDao.findById(stockDTO.getItemEntity());
        if (itemEntityOptional.isPresent()) {
            Item itemEntity = itemEntityOptional.get();
            stockEntity.setItemEntity(itemEntity);
        } else {
            throw new RuntimeException("Item not found with ID: " + stockDTO.getItemEntity());
        }
        stockDAO.saveAndFlush(stockEntity);
    }

    @Override
    public Iterable<StockDTO> getStocksByBranch(Branch branch) {
        return mapping.toStockDTOsByBranch(stockDAO.findByBranch(branch));
    }

    @Override
    public Iterable<StockDTO> getAllStock() {
        return mapping.toStockDTOs(stockDAO.findAll());
    }

    @Override
    public StockDTO getStockByID(String stockID) {
        return mapping.toStockDTO(stockDAO.findById(stockID).orElseThrow(() -> new RuntimeException("Supplier not found")));
    }

    @Override
    public void deleteStock(String stockID) {
        stockDAO.deleteById(stockID);
    }


//    @Override
//    public Iterable<StockDTO> getStocksByBranch(String branch) {
//        return mapping.toStockDTOs(stockDAO.findAllByBranch(Branch.valueOf(branch)));
//    }

    private String generateNextStockId() {
        Stock stock = stockDAO.findFirstByOrderByStockIdDesc();
        if (stock == null) {
            return "STK-001";
        }
        String lastStockId = stock.getStockId();
        int lastId = Integer.parseInt(lastStockId.split("-")[1]);
        int nextId = lastId + 1;
        return "STK-" + String.format("%03d", nextId);
    }
}
