package lk.ijse.gdse.aad.spring_boot_coursework.controller;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.GenderDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.OccasionDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.VarietyDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.service.GenderService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.OccasionService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.VarietyService;
import lombok.RequiredArgsConstructor;
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
    @DeleteMapping("/deleteGender")
    public boolean deleteGender(@PathVariable String genderCode){
        return genderService.deleteGender(genderCode);
    }
    @GetMapping("/getAllGenders")
    public GenderDTO[] getAllGenders(){
        return genderService.getAllGenders();
    }
    @GetMapping("/getGender")
    public GenderDTO getGender(@PathVariable String genderCode){
        return genderService.getGender(genderCode);
    }




    ////////////////////////////////////////////////////////////////////////////
    @PostMapping("/saveOccasion")
    public boolean saveOccasion(@Validated @RequestBody OccasionDTO occasionDTO){
        return occasionService.saveOccasion(occasionDTO);
    }
    @PatchMapping("/updateOccasion")
    public boolean updateOccasion(@Validated @RequestBody OccasionDTO occasionDTO){
        return occasionService.updateOccasion(occasionDTO.getOccasionCode(),occasionDTO);
    }
    @DeleteMapping("/deleteOccasion")
    public boolean deleteOccasion(@PathVariable String occasionCode){
        return occasionService.deleteOccasion(occasionCode);
    }

    @GetMapping("/getAllOccasions")
    public OccasionDTO[] getAllOccasions(){
        return occasionService.getAllOccasions();
    }
    @GetMapping("/getOccasion")
    public OccasionDTO getOccasion(@PathVariable String occasionCode){
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
    @DeleteMapping("/deleteVariety")
    public boolean deleteVariety(@PathVariable String varietyCode){
        return varietyService.deleteVariety(varietyCode);
    }

    @GetMapping("/getAllVarieties")
    public VarietyDTO[] getAllVarieties(){
        return varietyService.getAllVarieties();
    }

    @GetMapping("/getVariety")
    public VarietyDTO getVariety(@PathVariable String varietyCode){
        return varietyService.getVariety(varietyCode);
    }
}