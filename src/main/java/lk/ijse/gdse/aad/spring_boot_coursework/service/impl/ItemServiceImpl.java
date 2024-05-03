package lk.ijse.gdse.aad.spring_boot_coursework.service.impl;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.ItemDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.Item;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.NotFoundException;
import lk.ijse.gdse.aad.spring_boot_coursework.repo.ItemDao;
import lk.ijse.gdse.aad.spring_boot_coursework.service.ItemService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final Mapping mapping;

    private final ItemDao itemDao;
    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) {
        return mapping.toItemDTO((itemDao
                .save(mapping.toItem(itemDTO))));
    }
    @Override
    public Iterable<ItemDTO> getAllItems() {
        return mapping.toItemDTOs(itemDao.findAll());
    }

    @Override
    public ItemDTO getItem(String id) {
        return mapping.toItemDTO(itemDao.findById(id).orElseThrow(() -> new RuntimeException("Item not found")));
    }

    @Override
    public boolean deleteItem(String id) {
        Optional<Item> itemOptional = itemDao.findById(id);
        if (!itemOptional.isPresent()) throw new NotFoundException("Item");{
            itemOptional.ifPresent(itemDao::delete);
            return true;
        }
    }

    @Override
    public boolean updateItem(String itemCode, ItemDTO itemDTO) {
        Optional<Item> itemOptional = itemDao.findById(itemCode);
        if (!itemOptional.isPresent()) throw new NotFoundException("Item");{
            itemOptional.get().setItem_code(itemCode);
            itemOptional.get().setItem_desc(itemDTO.getItem_desc());
            itemOptional.get().setItem_qty(itemDTO.getItem_qty());
            itemOptional.get().setItem_pic(itemDTO.getItem_pic());
            itemOptional.get().setCategory(itemDTO.getCategory());
            itemOptional.get().setSize(Integer.valueOf(itemDTO.getItem_size()));
            itemOptional.get().setStatus(itemDTO.getStatus());
            itemOptional.ifPresent(itemDao::saveAndFlush);
            return true;
        }
    }

}
