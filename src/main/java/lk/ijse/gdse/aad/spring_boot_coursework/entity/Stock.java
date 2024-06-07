package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Branch;
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
    private int qty;
    private Double unitBuyingPrice;
    private Double unitSellingPrice;
    @Enumerated(EnumType.STRING)
    private Branch branch;

//    @JsonManagedReference
    @JsonIgnoreProperties("stockEntities")
    @ManyToOne
    @JoinColumn(name = "supplierCode",nullable = false)
    private Supplier supplierEntity;


//    @JsonBackReference
    @JsonIgnoreProperties("stocks")
    @ManyToOne
    @JoinColumn(name = "itemCode")
    private Item itemEntity;
    private Date suppliedDate;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "stockEntity",cascade = CascadeType.ALL)
//    private List<StockSize> stockSizeEntities;
}
