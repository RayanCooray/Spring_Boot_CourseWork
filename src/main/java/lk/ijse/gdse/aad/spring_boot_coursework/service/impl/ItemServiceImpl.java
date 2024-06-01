package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.EmployeeDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.ItemDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.*;
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

import java.util.List;
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
        // Generate the next unique item code
        itemDTO.setItem_code(generateNextOccasionID(itemDTO));
        System.out.println("----------------------"+ itemDTO.getGenderEntity());
        System.out.println("----------------------" +itemDTO.getOccasionEntity());
        System.out.println("----------------------" +itemDTO.getVarietyEntity());

        System.out.println("Generated Item Code: " + itemDTO.getItem_code());

        // Map DTO to entity
        Item itemEntity = mapping.toItemEntity(itemDTO);

        // Set the ID on the itemEntity explicitly
        itemEntity.setItemCode(itemDTO.getItem_code());

        // Fetch and set related entities
        Optional<MenWomenItem> genderEntity = genderDao.findById(itemDTO.getGenderEntity());
        if (genderEntity.isPresent()) {
            MenWomenItem genderEntity1 = genderEntity.get();
            itemEntity.setGenderEntity(genderEntity1);
        }

        Optional<Occasion> occasionEntity = occasionDao.findById(itemDTO.getOccasionEntity());
        if (occasionEntity.isPresent()) {
            Occasion occasionEntity1 = occasionEntity.get();
            itemEntity.setOccasionEntity(occasionEntity1);
        }

        Optional<Variety> varietyEntity = varirtyDao.findById(itemDTO.getVarietyEntity());
        if (varietyEntity.isPresent()) {
            Variety varietyEntity1 = varietyEntity.get();
            itemEntity.setVarietyEntity(varietyEntity1);
        }

        // Save the item entity
        itemDao.save(itemEntity);
    }


//    public String generateNextOccasionID(ItemDTO itemDTO) {
//        // Fetch the last generated item code
//        Item lastGen = itemDao.findFirstByOrderByItemCodeDesc();
//
//        // Extract the IDs from the itemDTO
//        String genderId = itemDTO.getGenderEntity();  // Assuming getId() returns the ID as a String
//        String occasionId = itemDTO.getOccasionEntity();  // Assuming getId() returns the ID as a String
//          // Assuming getId() returns the ID as a String
//
//        // Form the prefix for the new item code
//        String newCodePrefix =  occasionId + genderId;
//
//        if (lastGen == null) {
//            // Return the initial code with the relevant prefix
//            return newCodePrefix + "0001";
//        }
//
//        String lastGenOccasionCode = lastGen.getItemCode();
//        String lastPrefix = lastGenOccasionCode.substring(0, 3); // Extract prefix from last code
//
//        int lastId = Integer.parseInt(lastGenOccasionCode.substring(3)); // Extract number from last code
//
//        // If the prefix is the same, increment the number, else start from 1
//        int nextId = (lastPrefix.equals(newCodePrefix)) ? lastId + 1 : 1;
//
//        // Format the new code with the prefix and the new number
//        return newCodePrefix + String.format("%05d", nextId);
//    }


//public String generateNextOccasionID(ItemDTO itemDTO) {
//    // Fetch the last generated item code
//    Item lastGen = itemDao.findFirstByOrderByItemCodeDesc();
//
//    // Extract the IDs from the itemDTO
//    String genderId = itemDTO.getGenderEntity();
//    String occasionId = itemDTO.getOccasionEntity();
//
//    // Form the prefix for the new item code
//    String newCodePrefix = occasionId + genderId + "ITEM";
//
//    if (lastGen == null) {
//        // Return the initial code with the relevant prefix
//        return newCodePrefix + "00001";
//    }
//
//    String lastGenOccasionCode = lastGen.getItemCode();
//    String lastPrefix = lastGenOccasionCode.substring(0, newCodePrefix.length()); // Extract prefix from last code
//
//    int lastId = Integer.parseInt(lastGenOccasionCode.substring(newCodePrefix.length())); // Extract number from last code
//
//    // If the prefix is the same, increment the number, else start from 1
//    int nextId = (lastPrefix.equals(newCodePrefix)) ? lastId + 1 : 1;
//
//    // Format the new code with the prefix and the new number
//    return newCodePrefix + String.format("%05d", nextId);
//}

    public String generateNextOccasionID(ItemDTO itemDTO) {
        Item lastGen = itemDao.findFirstByOrderByItemCodeDesc();

        // Extract the IDs from the itemDTO
        String genderId = itemDTO.getGenderEntity();
        String occasionId = itemDTO.getOccasionEntity();

        // Determine the next unique number
        int nextId = 1;
        if (lastGen != null) {
            String lastGenOccasionCode = lastGen.getItemCode();
            String lastIdStr = lastGenOccasionCode.substring(4, 7);
            nextId = Integer.parseInt(lastIdStr) + 1;
        }

        String newCode = String.format("ITEM%03d%s%s", nextId, occasionId, genderId);

        return newCode;
    }



    @Override
    public boolean update(ItemDTO itemDTO, String itemCode) {
        if(!itemDao.existsById(itemCode)) throw new NotFoundException("Gender Not Found");
        Item itemEntity = mapping.toItemEntity(itemDTO);
        Optional<MenWomenItem> genderEntity = genderDao.findById(itemDTO.getGenderEntity());
        if (genderEntity.isPresent()){
            MenWomenItem genderEntity1 = genderEntity.get();
            itemEntity.setGenderEntity(genderEntity1);
        };
        Optional<Occasion> occasionEntity = occasionDao.findById(itemDTO.getOccasionEntity());
        if (occasionEntity.isPresent()){
            Occasion occasionEntity1 = occasionEntity.get();
            itemEntity.setOccasionEntity(occasionEntity1);
        }
        Optional<Variety> varietyEntity = varirtyDao.findById(itemDTO.getVarietyEntity());
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
