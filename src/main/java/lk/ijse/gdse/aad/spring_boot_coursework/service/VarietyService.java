package lk.ijse.gdse.aad.spring_boot_coursework.service;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.VarietyDTO;

public interface VarietyService {
    boolean saveVariety(VarietyDTO varietyDTO);

    boolean updateVariety(String varietyCode, VarietyDTO varietyDTO);

    boolean deleteVariety(String varietyCode);

    VarietyDTO[] getAllVarieties();

    VarietyDTO getVariety(String varietyCode);
}
