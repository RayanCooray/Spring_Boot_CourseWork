package lk.ijse.gdse.aad.spring_boot_coursework.entity;


import jakarta.persistence.*;
import lk.ijse.gdse.aad.spring_boot_coursework.Enum.Payment_Method;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    private String orderNo;
    private Timestamp purchasedDate;
    private int addedPoints;
    @Enumerated(EnumType.STRING)
    private Payment_Method paymentMethod;

    @ManyToOne
    @JoinColumn(name = "cutomerId",nullable = false)
    private Customer customerEntity;

    @ManyToOne
    @JoinColumn(name = "email",nullable = false)
    private User userEntity;

//    @OneToMany(mappedBy = "orderEntity",cascade = CascadeType.ALL)
//    private List<StockSizeOrderDetailsEntity> stockSizeOrderDetailsEntities ;
}
