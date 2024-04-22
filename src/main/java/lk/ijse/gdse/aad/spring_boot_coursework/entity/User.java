package lk.ijse.gdse.aad.spring_boot_coursework.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
@Entity
public class User implements UserDetails {
        @Id
        private String username_code;
        private String email;
        private String Password;
        @Enumerated(EnumType.STRING)
        private Access_Role role;
        @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
        private Employee employee;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
                grantedAuthoritySet.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
                return grantedAuthoritySet;
        }

        @Override
        public String getUsername() {
                return email;
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }
}
