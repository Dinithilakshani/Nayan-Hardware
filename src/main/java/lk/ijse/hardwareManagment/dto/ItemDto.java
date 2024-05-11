package lk.ijse.hardwareManagment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String  code;
    private String desctription;
    private  String Qty;
    private String price;

}
