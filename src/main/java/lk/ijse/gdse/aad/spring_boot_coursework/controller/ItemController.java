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
    public String healthTest() {
        return "Item Health  Test";
    }

    public static String generateID() {
        counter++;
        return String.format("ITEM%03d", counter);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveItem(@RequestPart ("itemDesc") String itemDesc,
                         @RequestPart ("pic") String pic,
                         @RequestPart ("status") String status,
                         @RequestPart ("genderCode") String genderCode,
                         @RequestPart ("occasionCode") String occasionCode,
                         @RequestPart ("varietyCode") String varietyCode){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItem_code(generateID());
        itemDTO.setItem_desc(itemDesc);
        itemDTO.setStatus(status);
        itemDTO.setGenderCode(genderCode);
        itemDTO.setOccasionCode(occasionCode);
        itemDTO.setVarietyCode(varietyCode);
        String Item_Picture = Imp.convertBase64(pic);
        itemDTO.setItem_pic(Item_Picture);
        itemService.saveItem(itemDTO);
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean updateItem(@RequestPart ("item_code") String itemCode,
                              @RequestPart ("itemDesc") String itemDesc,
                              @RequestPart ("pic") String pic,
                              @RequestPart ("status") String status,
                              @RequestPart ("genderCode") String genderCode,
                              @RequestPart ("occasionCode") String occasionCode,
                              @RequestPart ("varietyCode") String varietyCode){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItem_code(itemCode);
        itemDTO.setItem_desc(itemDesc);
        itemDTO.setStatus(status);
        itemDTO.setGenderCode(genderCode);
        itemDTO.setOccasionCode(occasionCode);
        itemDTO.setVarietyCode(varietyCode);
        String Item_Picture = Imp.convertBase64(pic);
        itemDTO.setItem_pic(Item_Picture);
        return  itemService.update(itemDTO,itemCode);
    }
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean deleteItem(@RequestPart("itemCode") String itemCode){
        return itemService.deleteItem(itemCode);
    }
    @GetMapping(value = "/GetItem",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ItemDTO getItem(@RequestPart("itemCode") String itemCode){
        return itemService.getItem(itemCode);
    }
    @GetMapping(value = "/getAllItems")
    public Iterable<ItemDTO> getAllItems(){
        return itemService.getAllItems();
    }
}