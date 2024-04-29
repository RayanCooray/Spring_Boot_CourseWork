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
@Table(name = "size")
public class Size {
    @Id
    private String sizeCode;
    private String sizeDesc;

    @OneToMany(mappedBy = "sizeEntity",cascade = CascadeType.ALL)
    private List<StockSize> stockSizeEntities;
}
