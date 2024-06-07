package lk.ijse.gdse.aad.spring_boot_coursework.dto;


import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Branch;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
    private String stockId;
    private String supplierEntity;
    private Date suppliedDate;
    private String itemEntity;
    private int qty;
    private Double unitBuyingPrice;
    private Double unitSellingPrice;
    private Branch branch;
}
