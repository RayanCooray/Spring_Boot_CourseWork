package lk.ijse.gdse.aad.spring_boot_coursework.repo;

import lk.ijse.gdse.aad.spring_boot_coursework.entity.MenWomenItem;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderDao extends JpaRepository<MenWomenItem,String> {

    MenWomenItem findFirstByOrderByGenderCodeDesc();
}
