package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.TransactionDTO;
import lk.ijse.dto.UserDTO;

import java.util.List;

public interface TransactionBO extends SuperBO {
    boolean saveTransaction(TransactionDTO transactionDTO, UserDTO userDTO , BookDTO bookDTO);
    List<UserDTO> getAllUser();
    List<BookDTO> getAllBooks();
}
