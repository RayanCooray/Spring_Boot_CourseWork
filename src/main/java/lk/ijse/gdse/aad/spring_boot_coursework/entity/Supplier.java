package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import jakarta.persistence.*;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    private String supplier_id;
    private String supplier_name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String address_line_01;
    private String address_line_02;
    private String address_line_03;
    private String address_line_04;
    private String address_line_05;
    private String address_line_06;
    private String contact_no_1;
    private String contact_no_2;
    private String email;
    @OneToMany(mappedBy = "supplierEntity",cascade = CascadeType.ALL)
    private List<Stock> stockEntities;
}
