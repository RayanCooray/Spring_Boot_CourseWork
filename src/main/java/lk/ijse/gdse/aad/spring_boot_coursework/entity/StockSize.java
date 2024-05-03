package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "StockSize")
public class StockSize{

    @Id
    private String stockSizeId;
    private int qty;
    private Double unitBuyingPrice;
    private Double unitSellingPrice;
    private Double profit;
    private Double profitMargin;

    @ManyToOne
    @JoinColumn(name = "stockId")
    private Stock stockEntity;

    @ManyToOne
    @JoinColumn(name = "sizeCode")
    private Size sizeEntity;

    @OneToMany(mappedBy = "stockSizeEntity",cascade = CascadeType.ALL)
    private List<StockOrderDetail> stockSizeOrderDetailsEntities;
}