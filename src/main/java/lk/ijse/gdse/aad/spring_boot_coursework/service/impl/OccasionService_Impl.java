package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.OccasionDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.MenWomenItem;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Occasion;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.NotFoundException;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.OccasionDao;
import lk.ijse.gdse.aad.spring_boot_coursework.service.OccasionService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OccasionService_Impl implements OccasionService {
    private final OccasionDao occasionDao;
    private final Mapping mapping;
    @Override
    public boolean saveOccasion(OccasionDTO occasionDTO) {
//        occasionDTO.setOccasionCode(generateNextOccasionID());
        System.out.println("==========================================================================================================================================="+occasionDTO);

        Occasion occasion = mapping.toOccasion(occasionDTO);
        occasion.setOccasionCode(occasion.getOccasionCode()); // Ensure supplierId is set
//
//        return mapping.toGenderDTO(genderService.save(menWomenItem) != null);
        return occasionDao.save(mapping.toOccasion(occasionDTO))!= null;
    }

    @Override
    public boolean updateOccasion(String occasionCode, OccasionDTO occasionDTO) {
        if(!occasionDao.existsById(occasionCode)) throw new NotFoundException("Gender Not Found");
        occasionDao.save(mapping.toOccasion(occasionDTO));
        return true;
    }

    @Override
    public boolean deleteOccasion(String occasionCode) {
        if(!occasionDao.existsById(occasionCode)) throw new NotFoundException("Gender Not Found");
        occasionDao.deleteById(occasionCode);
        return true;
    }

    @Override
    public OccasionDTO[] getAllOccasions() {
        return mapping.toOccasionDTOs(occasionDao.findAll());
    }

    @Override
    public OccasionDTO getOccasion(String occasionCode) {
        if(!occasionDao.existsById(occasionCode)) throw new NotFoundException("Gender Not Found");
        return mapping.toOccasionDTO(occasionDao.findById(occasionCode).get());
    }

    public String generateNextOccasionID() {
        Occasion lastGen = occasionDao.findFirstByOrderByOccasionCodeDesc();
        if (lastGen == null) {
            return "OCCA-001";
        }
        String lastGenOccasionCode = lastGen.getOccasionCode();
        int lastId = Integer.parseInt(lastGenOccasionCode.split("-")[1]);
        int nextId = lastId + 1;
        return "OCCA-" + String.format("%03d", nextId);
    }
}
