package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import jakarta.persistence.*;
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
@Table(name = "Item")
public class Item {
    @Id
    private String item_code;
    private String item_desc;
    private int item_qty;
    @Column(columnDefinition = "LONGTEXT")
    private String item_pic;
    private String category;
    private Integer size;

    private String status;

   @OneToMany(mappedBy ="itemEntity",cascade = CascadeType.ALL)
   private List<Stock> stocks;

    @ManyToOne
    @JoinColumn(name = "occasionCode")
    private Occasion  occasionEntity;

    @ManyToOne
    @JoinColumn(name = "varietyCode")
    private Variety varietyEntity;

    @ManyToOne
    @JoinColumn(name = "genderCode")
    private MenWomenItem genderEntity;
}
