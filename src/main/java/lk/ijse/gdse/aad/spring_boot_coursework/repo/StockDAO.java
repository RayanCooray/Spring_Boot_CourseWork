package lk.ijse.gdse.aad.spring_boot_coursework.repo;

import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Branch;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Employee;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface StockDAO extends JpaRepository<Stock,String> {
    Stock findFirstByOrderByStockIdDesc();

    List<Stock> findByBranch(Branch branch);
}
