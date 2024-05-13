package lk.ijse.hardwareManagment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderdetailsDto {






        private String orderId;

        private String description;
        private int unitPrice;
        private int QtyOnHand;
        private  int qty;
        private double amount;

    }


