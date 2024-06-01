package lk.ijse.gdse.aad.spring_boot_coursework.controller;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.ItemDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.service.ItemService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Imp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
@EnableWebMvc
public class ItemController {

    @Autowired
    private final ItemService itemService;

    @GetMapping("/health")
    public String healthTest() {
        return "Item Health  Test";
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveItem(@RequestPart ("item_desc") String itemDesc,
                         @RequestPart ("item_pic") String pic,
                         @RequestPart ("status") String status,
                         @RequestPart ("genderEntity") String genderCode,
                         @RequestPart ("occasionEntity") String occasionCode,
                         @RequestPart ("varietyEntity") String varietyCode){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItem_desc(itemDesc);
        itemDTO.setStatus(status);
        itemDTO.setGenderEntity(genderCode);
        itemDTO.setOccasionEntity(occasionCode);
        itemDTO.setVarietyEntity(varietyCode);
        itemDTO.setItem_pic(pic);
        itemService.saveItem(itemDTO);
    }

//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public void saveItem(@RequestBody ItemDTO  itemDTO){
////        ItemDTO itemDTO = new ItemDTO();
////        itemDTO.setItem_code(generateID());
////        itemDTO.setItem_desc(itemDesc);
////        itemDTO.setStatus(status);
////        itemDTO.setGenderCode(genderCode);
////        itemDTO.setOccasionCode(occasionCode);
////        itemDTO.setVarietyCode(varietyCode);
////        String Item_Picture = Imp.convertBase64(pic);
////        itemDTO.setItem_pic(Item_Picture);
//        itemService.saveItem(itemDTO);
//    }

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
        itemDTO.setGenderEntity(genderCode);
        itemDTO.setOccasionEntity(occasionCode);
        itemDTO.setVarietyEntity(varietyCode);
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
    public Iterable<ItemDTO> getAllofItems(){
        return itemService.getAllItems();
    }
}