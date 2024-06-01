package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.VarietyDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.MenWomenItem;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Variety;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.NotFoundException;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.VarirtyDao;
import lk.ijse.gdse.aad.spring_boot_coursework.service.VarietyService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VarietyService_Impl implements VarietyService {
    private final Mapping mapping;
    private final VarirtyDao varirtyDao;
    @Override
    public boolean saveVariety(VarietyDTO varietyDTO) {
//        varietyDTO.setVarietyCode(generateNextVarietyID());
        System.out.println("==========================================================================================================================================="+varietyDTO);

        Variety variety = mapping.toVariety(varietyDTO);
        variety.setVarietyCode(varietyDTO.getVarietyCode()); // Ensure supplierId is set
//
        return varirtyDao.save(mapping.toVariety(varietyDTO))!= null;
    }

    @Override
    public boolean updateVariety(String varietyCode, VarietyDTO varietyDTO) {
        if (!varirtyDao.existsById(varietyCode)) throw
                new NotFoundException("No such variation");
        varirtyDao.save(mapping.toVariety(varietyDTO));
        return true;
    }

    @Override
    public boolean deleteVariety(String varietyCode) {
        if (!varirtyDao.existsById(varietyCode)) throw
                new NotFoundException("No such variation");
        varirtyDao.deleteById(varietyCode);
        return true;
    }

    @Override
    public VarietyDTO[] getAllVarieties() {
        return mapping.toVarietyDTOs(varirtyDao.findAll());
    }

    @Override
    public VarietyDTO getVariety(String varietyCode) {
        return mapping.toVarietyDTO(varirtyDao.findById(varietyCode).orElseThrow(() -> new NotFoundException("No such variation")));
    }


    @Override
    public String generateNextVarietyID() {
        Variety lastVAR = varirtyDao.findFirstByOrderByVarietyCodeDesc();
        if (lastVAR == null) {
            return "VARR-001";
        }
        String lastCustomerId = lastVAR.getVarietyCode();
        int lastId = Integer.parseInt(lastCustomerId.split("-")[1]);
        int nextId = lastId + 1;
        return "VARR-" + String.format("%03d", nextId);
    }
}
