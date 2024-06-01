package lk.ijse.gdse.aad.spring_boot_coursework.service;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.GenderDTO;

public interface GenderService {
    boolean saveGender(GenderDTO genderDTO);

    boolean updateGender(String genderCode, GenderDTO genderDTO);

    boolean deleteGender(String genderCode);

    String generateNextGenderID();

    GenderDTO[] getAllGenders();

    GenderDTO getGender(String genderCode);
}
