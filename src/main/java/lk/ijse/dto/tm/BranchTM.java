package lk.ijse.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchTM {
    private long id;
    private String branchIdNumber;
    private String branchName;
    private JFXButton update;
    private JFXButton delete;
}
