package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    private String item_code;
    private String item_desc;
    private int item_qty;
    private String item_pic;
    private String category;
    private int size;
    private String supplier_code;
    private String supplier_name;
    private double unit_price_sale;
    private double unit_price_buy;
    private double expected_profit;
    private double profit_margin;
    private String status;

    @ManyToMany(mappedBy = "inventories")
    private Set<Sale> sales =  new HashSet<>();

    @ManyToMany(mappedBy = "inventories")
    private Set<Supplier> suppliers = new HashSet<>();
}
