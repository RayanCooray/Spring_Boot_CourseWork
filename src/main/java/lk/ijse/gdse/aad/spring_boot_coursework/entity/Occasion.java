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
@Table (name = "Occassion")
public class Occasion {
    @Id
    private String occasionCode;
    private String occasionDesc;
    @OneToMany(mappedBy = "occasionEntity",cascade = CascadeType.ALL)
    private List<Item> itemEntities;
}
