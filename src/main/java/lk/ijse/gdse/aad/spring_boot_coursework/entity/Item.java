package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String itemCode;
    private String item_desc;
    private int item_qty;
    @Column(columnDefinition = "LONGTEXT")
    private String item_pic;

    private String status;

//    @JsonIgnore
    @JsonManagedReference
   @OneToMany(mappedBy ="itemEntity",cascade = CascadeType.ALL)
   private List<Stock> stocks;

//    @JsonIgnore
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "occasionCode")
    private Occasion  occasionEntity;

//    @JsonIgnore
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "varietyCode")
    private Variety varietyEntity;

//    @JsonIgnore
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "genderCode")
    private MenWomenItem genderEntity;
}
