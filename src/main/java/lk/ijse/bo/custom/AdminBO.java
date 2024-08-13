package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AdminDTO;
import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Admin;

import java.util.List;

public interface AdminBO extends SuperBO {
    boolean saveAdmin(AdminDTO adminDTO);
    boolean updateAdmin(AdminDTO admin);
    boolean deleteAdmin();
    List<AdminDTO> getAllAdmin();
    List<BookDTO> getAllBook();
}
