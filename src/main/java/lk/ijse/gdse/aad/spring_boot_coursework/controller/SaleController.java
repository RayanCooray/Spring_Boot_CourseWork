package lk.ijse.gdse.aad.spring_boot_coursework.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/SaleController")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class SaleController {
    private static int counter = 0;
    @GetMapping("/health")
    public String healthTest(){
        return "Sale Health  Test";
    }


}
