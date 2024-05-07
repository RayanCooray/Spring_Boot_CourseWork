package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.ItemDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Item;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.MenWomenItem;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Occasion;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Variety;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.NotFoundException;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.GenderDao;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.ItemDao;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.OccasionDao;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.VarirtyDao;
import lk.ijse.gdse.aad.spring_boot_coursework.service.ItemService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemDao itemDao;
    private final Mapping mapping;
    private final GenderDao genderDao;
    private final OccasionDao occasionDao;
    private final VarirtyDao varirtyDao;


    @Override
    public void saveItem(ItemDTO itemDTO) {
        Item itemEntity = mapping.toItemEntity(itemDTO);
        Optional<MenWomenItem> genderEntity = genderDao.findById(itemDTO.getGenderCode());
        if (genderEntity.isPresent()){
            MenWomenItem genderEntity1 = genderEntity.get();
            itemEntity.setGenderEntity(genderEntity1);
        };
        Optional<Occasion> occasionEntity = occasionDao.findById(itemDTO.getOccasionCode());
        if (occasionEntity.isPresent()){
            Occasion occasionEntity1 = occasionEntity.get();
            itemEntity.setOccasionEntity(occasionEntity1);
        }
        Optional<Variety> varietyEntity = varirtyDao.findById(itemDTO.getVarietyCode());
        if (varietyEntity.isPresent()){
            Variety varietyEntity1 = varietyEntity.get();
            itemEntity.setVarietyEntity(varietyEntity1);
        }
        itemDao.save(itemEntity);
    }

    @Override
    public boolean update(ItemDTO itemDTO, String itemCode) {
        if(!itemDao.existsById(itemCode)) throw new NotFoundException("Gender Not Found");
        Item itemEntity = mapping.toItemEntity(itemDTO);
        Optional<MenWomenItem> genderEntity = genderDao.findById(itemDTO.getGenderCode());
        if (genderEntity.isPresent()){
            MenWomenItem genderEntity1 = genderEntity.get();
            itemEntity.setGenderEntity(genderEntity1);
        };
        Optional<Occasion> occasionEntity = occasionDao.findById(itemDTO.getOccasionCode());
        if (occasionEntity.isPresent()){
            Occasion occasionEntity1 = occasionEntity.get();
            itemEntity.setOccasionEntity(occasionEntity1);
        }
        Optional<Variety> varietyEntity = varirtyDao.findById(itemDTO.getVarietyCode());
        if (varietyEntity.isPresent()){
            Variety varietyEntity1 = varietyEntity.get();
            itemEntity.setVarietyEntity(varietyEntity1);
        }

        itemDao.save(itemEntity);
        return true;
    }

    @Override
    public boolean deleteItem(String itemCode) {
        if(!itemDao.existsById(itemCode)) throw new NotFoundException("Item Not Found");
        itemDao.deleteById(itemCode);
        return true;
    }

    @Override
    public ItemDTO getItem(String itemCode) {
        if(!itemDao.existsById(itemCode)) throw new NotFoundException("Item Not Found");
        return mapping.toItemDTO(itemDao.findById(itemCode).get());
    }

    @Override
    public Iterable<ItemDTO> getAllItems() {
        return mapping.toItemDTOs(itemDao.findAll());
    }

}
