package lk.ijse.gdse.aad.spring_boot_coursework.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Orders")
@Entity
public class Order {
    @Id
    private String oder_no;
    @ManyToOne
    @JoinColumn(name = "customer_code")
    private Customer customer;
    private String added_point;
    private String Cashier_Name;
    private Double totalprice;
    private Payment_Method paymentMethod;
    private Date purchased_date;



}
