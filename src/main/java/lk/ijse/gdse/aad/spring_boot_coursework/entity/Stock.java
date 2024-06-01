package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "Stock")
public class Stock {
    @Id
    private String stockId;
    @ManyToOne
    @JoinColumn(name = "supplierCode",nullable = false)
    private Supplier supplierEntity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "itemCode")
    private Item itemEntity;
    private Date suppliedDate;

    @JsonIgnore
    @OneToMany(mappedBy = "stockEntity",cascade = CascadeType.ALL)
    private List<StockSize> stockSizeEntities;
}
