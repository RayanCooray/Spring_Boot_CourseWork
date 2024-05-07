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
    public boolean deleteGender(@RequestPart String genderCode){
        return genderService.deleteGender(genderCode);
    }
    @GetMapping("/getAllGenders")
    public GenderDTO[] getAllGenders(){
        return genderService.getAllGenders();
    }
    @GetMapping(value = "/getGender",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenderDTO getGender(@RequestPart String genderCode){
        return genderService.getGender(genderCode);
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
    @GetMapping(value = "/getOccasion",consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public OccasionDTO getOccasion(@RequestPart String occasionCode){
        return occasionService.getOccasion(occasionCode);
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
    @DeleteMapping(value = "/deleteVariety",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean deleteVariety(@RequestPart String varietyCode){
        return varietyService.deleteVariety(varietyCode);
    }

    @GetMapping("/getAllVarieties")
    public VarietyDTO[] getAllVarieties(){
        return varietyService.getAllVarieties();
    }

    @GetMapping(value = "/getVariety",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public VarietyDTO getVariety(@RequestPart String varietyCode){
        return varietyService.getVariety(varietyCode);
    }
    ///////////////////////////////////////////////////////////////////
}