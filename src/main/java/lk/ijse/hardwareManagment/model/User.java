package lk.ijse.hardwareManagment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class User {
    private String username;
    private String email;
    private String password;
}
