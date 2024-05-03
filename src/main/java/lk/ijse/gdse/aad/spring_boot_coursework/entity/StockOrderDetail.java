package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "StockOrderDetail")
public class StockOrderDetail {
    @Id
    private String stockSizeOrderDetailsId;
    private int qty;

    @ManyToOne
    @JoinColumn(name = "stockSizeId")
    private StockSize stockSizeEntity;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Sale orderEntity;
}
