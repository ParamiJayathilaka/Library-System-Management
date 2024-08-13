package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AdminDTO;

import java.util.List;

public interface LoginFormBO extends SuperBO {
    List<AdminDTO> getAllAdmin();
}
