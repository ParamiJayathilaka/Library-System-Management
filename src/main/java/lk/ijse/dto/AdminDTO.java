package lk.ijse.dto;

import lk.ijse.entity.Branches;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private long id;
    private String username;
    private String password;
}
