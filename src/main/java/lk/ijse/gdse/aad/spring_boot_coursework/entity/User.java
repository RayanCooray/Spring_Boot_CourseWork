package lk.ijse.gdse.aad.spring_boot_coursework.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
@Entity
public class User {
        @Id
        private String username_code;
        @OneToOne
        @JoinColumn(name = "code")
        private Employee employee;
        private String email;
        private String Password;
        private Access_Role role;
}
