package lk.ijse.gdse.aad.spring_boot_coursework.service;

import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Branch;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.EmployeeDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.StockDTO;
import org.springframework.http.ResponseEntity;

public interface StockService {
//    void saveStock(StockDTO stockDTO,String email);

    void saveStock(StockDTO stockDTO);

    void updateStock(StockDTO stockDTO);

    Iterable<StockDTO> getStocksByBranch(Branch branch);

    Iterable<StockDTO> getAllStock();

    StockDTO getStockByID(String stockID);

    void deleteStock(String stockID);
}
