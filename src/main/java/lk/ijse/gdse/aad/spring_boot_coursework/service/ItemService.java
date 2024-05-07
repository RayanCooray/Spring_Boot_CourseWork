package lk.ijse.gdse.aad.spring_boot_coursework.service;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.ItemDTO;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);

    boolean update(ItemDTO itemDTO, String itemCode);

    boolean deleteItem(String itemCode);

    ItemDTO getItem(String itemCode);

    Iterable<ItemDTO> getAllItems();

}
