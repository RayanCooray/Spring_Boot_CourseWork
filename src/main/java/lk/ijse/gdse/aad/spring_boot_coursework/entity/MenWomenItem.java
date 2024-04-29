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
@Table (name = "gender")
public class MenWomenItem {
    @Id
    private String genderCode;
    private String genderDesc;
    @OneToMany(mappedBy = "genderEntity",cascade = CascadeType.ALL)
    private List<Inventory> itemEntities;

}