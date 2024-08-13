package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
    List<BookDTO> getAllBooks();
    List<UserDTO> getAllUsers();
}
