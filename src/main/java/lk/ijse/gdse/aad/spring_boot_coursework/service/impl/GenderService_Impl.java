package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.GenderDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Customer;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.MenWomenItem;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Supplier;
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
//        genderDTO.setGenderCode(generateNextGenderID());
        System.out.println("==========================================================================================================================================="+genderDTO);

        MenWomenItem menWomenItem = mapping.toGender(genderDTO);
        menWomenItem.setGenderCode(genderDTO.getGenderCode()); // Ensure supplierId is set
//
//        return mapping.toGenderDTO(genderService.save(menWomenItem) != null);
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
    public String generateNextGenderID() {
        MenWomenItem lastGen = genderService.findFirstByOrderByGenderCodeDesc();
        if (lastGen == null) {
            return "GEND-001";
        }
        String lastCustomerId = lastGen.getGenderCode();
        int lastId = Integer.parseInt(lastCustomerId.split("-")[1]);
        int nextId = lastId + 1;
        return "GEND-" + String.format("%03d", nextId);
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
