package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.SizeDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.NotFoundException;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.SizeDao;
import lk.ijse.gdse.aad.spring_boot_coursework.service.SizeService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Size_Service_Impl implements SizeService {
    @Autowired
    private SizeDao sizeDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveSize(SizeDTO sizeDTO) {
        sizeDao.save(mapping.toSize(sizeDTO));
    }

    @Override
    public boolean updateSize(String id, SizeDTO sizeDTO) {
        if(!sizeDao.existsById(id)) throw new NotFoundException("Gender Not Found");
        sizeDao.save(mapping.toSize(sizeDTO));
        return true;
    }

    @Override
    public void deleteSize(String id) {
        if(!sizeDao.existsById(id)) throw new NotFoundException("Gender Not Found");
        sizeDao.deleteById(id);
    }

    @Override
    public Object getAllSizes() {
        return mapping.toSizeDTOs(sizeDao.findAll());
    }


}
