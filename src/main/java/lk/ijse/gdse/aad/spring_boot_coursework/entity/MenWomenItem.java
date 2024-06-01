package lk.ijse.gdse.aad.spring_boot_coursework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import lk.ijse.gdse.aad.spring_boot_coursework.IdGen.CustomIdGenerator;

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
    @JsonIgnore
    private List<Item> itemEntities;

}