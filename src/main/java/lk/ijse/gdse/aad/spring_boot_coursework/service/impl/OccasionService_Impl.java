package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.OccasionDTO;
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
}
