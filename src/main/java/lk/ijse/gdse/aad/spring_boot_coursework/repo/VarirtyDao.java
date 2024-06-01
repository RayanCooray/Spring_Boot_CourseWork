package lk.ijse.gdse.aad.spring_boot_coursework.repo;

import lk.ijse.gdse.aad.spring_boot_coursework.entity.Variety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.annotation.Validated;

@Validated
public interface VarirtyDao extends JpaRepository<Variety,String> {
    Variety findFirstByOrderByVarietyCodeDesc();

}
