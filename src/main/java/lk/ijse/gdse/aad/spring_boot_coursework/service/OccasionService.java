package lk.ijse.gdse.aad.spring_boot_coursework.service;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.OccasionDTO;

public interface OccasionService {
    boolean saveOccasion(OccasionDTO occasionDTO);

    boolean updateOccasion(String occasionCode, OccasionDTO occasionDTO);

    boolean deleteOccasion(String occasionCode);

    OccasionDTO[] getAllOccasions();

    OccasionDTO getOccasion(String occasionCode);
}
