package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.GenderDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.NotFoundException;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.GenderDao;
import lk.ijse.gdse.aad.spring_boot_coursework.service.GenderService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class GenderService_Impl implements GenderService {
    private final GenderDao genderService;

    private final Mapping mapping;
    @Override
    public boolean saveGender(GenderDTO genderDTO) {
        return genderService.save(mapping.toGender(genderDTO))!= null;
    }

    @Override
    public boolean updateGender(String genderCode, GenderDTO genderDTO) {
        if(!genderService.existsById(genderCode)) throw new NotFoundException("Gender Not Found");
        genderService.save(mapping.toGender(genderDTO));
        return true;
    }

    @Override
    public boolean deleteGender(String genderCode) {
        if(!genderService.existsById(genderCode)) throw new NotFoundException("Gender Not Found");
        genderService.deleteById(genderCode);
        return true;
    }

    @Override
    public GenderDTO[] getAllGenders() {
        return mapping.toGenderDTOs(genderService.findAll());
    }

    @Override
    public GenderDTO getGender(String genderCode) {
        return mapping.toGenderDTO(genderService.findById(genderCode).orElseThrow(() -> new NotFoundException("Gender Not Found")));
    }
}
