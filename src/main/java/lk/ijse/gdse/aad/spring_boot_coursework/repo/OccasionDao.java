package lk.ijse.gdse.aad.spring_boot_coursework.repo;

import lk.ijse.gdse.aad.spring_boot_coursework.entity.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccasionDao extends JpaRepository<Occasion,String> {
}
