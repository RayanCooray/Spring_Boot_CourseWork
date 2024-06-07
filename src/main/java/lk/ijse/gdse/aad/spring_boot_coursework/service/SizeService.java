package lk.ijse.gdse.aad.spring_boot_coursework.service;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.SizeDTO;

public interface SizeService {
    void saveSize(SizeDTO sizeDTO);

    boolean updateSize(String id, SizeDTO sizeDTO);

    void deleteSize(String id);

    Object getAllSizes();
}
