package lk.ijse.gdse.aad.spring_boot_coursework.repo;

import lk.ijse.gdse.aad.spring_boot_coursework.entity.Customer;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ItemDao extends JpaRepository<Item,String> {
}
