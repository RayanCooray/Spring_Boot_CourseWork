package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "Returns")
public class Return {
    @Id
    private String returnId;
    private Timestamp returnDate;
    @OneToOne(cascade = CascadeType.ALL)
    private StockOrderDetail stockSizeOrderDetailsEntity;
    private int qty;
}
