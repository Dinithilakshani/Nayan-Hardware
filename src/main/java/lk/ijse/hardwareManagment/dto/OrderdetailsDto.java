package lk.ijse.hardwareManagment.dto;

import lombok.*;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderdetailsDto {
    private String orderId;
    private String code;
    private String description;
    private double Price;
    private int qty;
    private double amount;



}



