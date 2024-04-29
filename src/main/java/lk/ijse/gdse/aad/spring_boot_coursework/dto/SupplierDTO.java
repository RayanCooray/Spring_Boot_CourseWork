package lk.ijse.gdse.aad.spring_boot_coursework.dto;

import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SupplierDTO {
    private String supplier_id;
    private String supplier_name;
    private Category category;
    private String address_line_01;
    private String address_line_02;
    private String address_line_03;
    private String address_line_04;
    private String address_line_05;
    private String address_line_06;
    private String contact_no_1;
    private String contact_no_2;
    private String email;


//    {
//        "supplier_id":"",
//            "supplier_name":"rayan",
//            "category":"Local",
//            "address_line_01":"30/9",
//            "address_line_02":"Fonseka Place",
//            "address_line_03":"Kalutara",
//            "address_line_04":"Westurn Porvince",
//            "address_line_05":"12000",
//            "address_line_06":"Sri Lanka",
//            "contact_no_1":"0712345566",
//            "contact_no_2":"0788810080",
//            "email":"rayan@gmail.com"
//    }

}
