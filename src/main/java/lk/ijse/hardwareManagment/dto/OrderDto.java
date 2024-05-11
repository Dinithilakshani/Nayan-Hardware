package lk.ijse.hardwareManagment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDto {
    private String orderId;

    private String description;
    private String QtyOnHand;
    private String amount;
    private String email;
}
