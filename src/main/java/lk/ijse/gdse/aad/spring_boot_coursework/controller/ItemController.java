package lk.ijse.gdse.aad.spring_boot_coursework.controller;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.ItemDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.service.ItemService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Imp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class ItemController {
    private static int counter = 0;

    @Autowired
    private final ItemService itemService;

    @GetMapping("/health")
    public String healthTest(){
        return "Item Health  Test";
    }

    public static String generateID() {
        counter++;
        return String.format("ITEM%03d", counter);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean saveItem(ItemDTO itemDTO, @RequestPart ("ItemPicture") String ItemPicture){
        String itempicture = Imp.convertBase64(ItemPicture);
        itemDTO.setItem_code(generateID());
        itemDTO.setItem_pic(itempicture);
        itemService.saveItem(itemDTO);
        return true;
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean updateItem(ItemDTO itemDTO, @RequestPart ("ItemPicture") String ItemPicture){
        String itempicture = Imp.convertBase64(ItemPicture);
        itemDTO.setItem_pic(itempicture);
        return itemService.updateItem(itemDTO.getItem_code(),itemDTO);
    }

    @GetMapping("/getAllItems")
    public Iterable<ItemDTO> getAllItems(){
        return itemService.getAllItems();
    }
    @GetMapping(value = "/getItemById",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ItemDTO getItem(@RequestPart("id") String id){
        return itemService.getItem(id);
    }
    @DeleteMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean deleteItem(@RequestPart ("id") String id){
        return itemService.deleteItem(id);
    }
}
