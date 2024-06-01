package lk.ijse.gdse.aad.spring_boot_coursework.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "Variety")
public class Variety {
    @Id
    private String varietyCode;
    private String varietyDesc;
    @OneToMany(mappedBy = "varietyEntity",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Item> itemEntities;
}
