package lk.ijse.hardwareManagment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
@NoArgsConstructor
public class SupplierDto {
    private String id;
    private String name;
    private String EmailAddress;
    private String Number;


}
