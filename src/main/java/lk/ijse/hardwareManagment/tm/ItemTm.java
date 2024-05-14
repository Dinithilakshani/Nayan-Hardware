package lk.ijse.hardwareManagment.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ItemTm {

    private String itemCode;

    private String desctription;
    private  int qty;
    private double price;


}
