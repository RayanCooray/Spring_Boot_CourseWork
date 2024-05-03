package lk.ijse.gdse.aad.spring_boot_coursework.service;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.ItemDTO;

public interface ItemService {
    ItemDTO saveItem(ItemDTO itemDTO);

    Iterable<ItemDTO> getAllItems();

    ItemDTO getItem(String id);

    boolean deleteItem(String id);

    boolean updateItem(String itemCode, ItemDTO itemDTO);
}
