package lk.ijse.gdse.aad.spring_boot_coursework.controller;


import lk.ijse.gdse.aad.spring_boot_coursework.dto.GenderDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.OccasionDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.SizeDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.VarietyDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.NotFoundException;
import lk.ijse.gdse.aad.spring_boot_coursework.service.GenderService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.OccasionService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.SizeService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.VarietyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    private final SizeService sizeService;
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


//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/sizeSave")
//    public ResponseEntity<?> saveSize(@Validated @RequestBody SizeDTO sizeDTO,
//                                      BindingResult bindingResult){
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
//                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
//        }
//
//        try {
//            sizeService.saveSize(sizeDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Gender Details saved Successfully.");
//        } catch (Exception exception) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
//                    body("Internal server error | Gender saved Unsuccessfully.\nMore Details\n"+exception);
//        }
//    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/sizeUpdate")
    public ResponseEntity<String> updateSize(@Validated @RequestBody SizeDTO sizeDTO,
                                             BindingResult bindingResult,
                                             @RequestParam ("id") String id) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            sizeService.updateSize(id, sizeDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Size Details Updated Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Size not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Size Details Gender Unsuccessfully.\nMore Reason\n" + exception);
        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/sizeDelete")
    public ResponseEntity<String> deleteSize(@RequestParam String id){
        try {
            sizeService.deleteSize(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Size Details deleted Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Size not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Size Details deleted Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @GetMapping("/sizeGetAll")
    public ResponseEntity<?> getAllSizes(){
        try {
            return ResponseEntity.ok(sizeService.getAllSizes());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Size Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }


    ///////////////////////////////////


}