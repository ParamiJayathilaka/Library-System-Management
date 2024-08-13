package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AdminDTO;
import lk.ijse.dto.RegisterDTO;
import lk.ijse.dto.UserDTO;

import java.util.List;

public interface SettingFormBO extends SuperBO {
    List<RegisterDTO> getAllUser();
    List<AdminDTO> getAllAdmin();
    boolean updateAdminUsername(AdminDTO adminDTO);
    boolean updateUserUsername(UserDTO userDTO);
    boolean updateUserPassword(UserDTO userDTO);
    boolean updateAdminPassword(AdminDTO adminDTO);

}
