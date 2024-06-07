package lk.ijse.gdse.aad.spring_boot_coursework.controller;


import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Branch;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.StockDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.NotFoundException;
import lk.ijse.gdse.aad.spring_boot_coursework.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
@EnableWebMvc
public class StockController{
    private final StockService stockService;



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/StockSave")
    public ResponseEntity<?> saveStock(@Validated @RequestBody StockDTO stockDTO,
                                       BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            stockService.saveStock(stockDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Gender Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Gender saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/StockUpdate")
    public ResponseEntity<?> updateStock(@Validated @RequestBody StockDTO stockDTO,
                                         BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            stockService.updateStock(stockDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Stock Updated.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Gender saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping("/GetStocksByBranch/{Branch}")
    public Iterable<StockDTO> getStocksByBranch(@PathVariable("Branch") Branch branch){
        return stockService.getStocksByBranch(branch);
    }


    @GetMapping("/getAllStock")
    public ResponseEntity<?> getAllStock(){
        try {
            return ResponseEntity.ok(stockService.getAllStock());
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employees not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Employees Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @GetMapping("/getStockByID/{stockID}")
    public ResponseEntity<?> getStockByID(@PathVariable("stockID") String stockID){
        try {
            return ResponseEntity.ok(stockService.getStockByID(stockID));
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employees not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Employees Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @DeleteMapping("/deleteStock/{stockID}")
    public void deleteStock(@PathVariable("stockID") String stockID){
        stockService.deleteStock(stockID);
    }
}