package lk.ijse.gdse.aad.spring_boot_coursework.controller;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.GenderDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.OccasionDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.VarietyDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.service.GenderService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.OccasionService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.VarietyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inset")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class InventorySetController {
    private final GenderService genderService;
    private final OccasionService occasionService;
    private final VarietyService varietyService;
    @GetMapping("/health")
    public String healthTest(){
        return "Health Test";
    }




    @PostMapping("/saveGender")
    public boolean saveGender(@Validated @RequestBody  GenderDTO genderDTO){
        return genderService.saveGender(genderDTO);
    }

    @PatchMapping("/updateGender")
    public boolean updateGender(@Validated @RequestBody GenderDTO genderDTO){
        return genderService.updateGender(genderDTO.getGenderCode(),genderDTO);
    }
    @DeleteMapping(value = "/deleteGender",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean deleteGender(@RequestPart String id){
        return genderService.deleteGender(id);
    }
    @GetMapping("/getAllGenders")
    public GenderDTO[] getAllGenders(){
        return genderService.getAllGenders();
    }
    @GetMapping(value = "Getgender/{id}",produces = "application/json")
    public GenderDTO getGender(@PathVariable ("id") String id){
        return genderService.getGender(id);
    }




    ////////////////////////////////////////////////////////////////////////////
    @PostMapping("/saveOccasion")
    public boolean saveOccasion(@Validated @RequestBody OccasionDTO occasionDTO){
        return occasionService.saveOccasion(occasionDTO);
    }
    @PatchMapping(value = "/updateOccasion",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateOccasion(@Validated @RequestBody OccasionDTO occasionDTO){
        return occasionService.updateOccasion(occasionDTO.getOccasionCode(),occasionDTO);
    }
    @DeleteMapping(value = "/deleteOccasion",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean deleteOccasion(@RequestPart String occasionCode){
        return occasionService.deleteOccasion(occasionCode);
    }

    @GetMapping("/getAllOccasions")
    public OccasionDTO[] getAllOccasions(){
        return occasionService.getAllOccasions();
    }
    @GetMapping(value = "/getOccasion/{id}",produces = "application/json"
    )
    public OccasionDTO getOccasion(@PathVariable String id){
        return occasionService.getOccasion(id);
    }

    ///////////////////////////////////////////////////////////////////


    @PostMapping("/saveVariety")
    public boolean saveVariety(@Validated @RequestBody VarietyDTO varietyDTO){
        return varietyService.saveVariety(varietyDTO);
    }
    @PatchMapping("/updateVariety")
    public boolean updateVariety(@Validated @RequestBody VarietyDTO varietyDTO){
        return varietyService.updateVariety(varietyDTO.getVarietyCode(),varietyDTO);
    }
    @DeleteMapping(value = "/deleteVariety/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean deleteVariety(@PathVariable String id){
        return varietyService.deleteVariety(id);
    }

    @GetMapping("/getAllVarieties")
    public VarietyDTO[] getAllVarieties(){
        return varietyService.getAllVarieties();
    }

    @GetMapping(value = "/getVariety/{id}",produces = "application/json")
    public VarietyDTO getVariety(@PathVariable String id){
        return varietyService.getVariety(id);
    }
    ///////////////////////////////////////////////////////////////////
}