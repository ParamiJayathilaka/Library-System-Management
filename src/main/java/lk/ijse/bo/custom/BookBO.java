package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDTO;

import java.util.List;

public interface BookBO extends SuperBO {
    boolean saveBook(BookDTO book);
    boolean deleteBook(long id);
    List<BookDTO> getAllBook();
    boolean updateBook(BookDTO bookDTO);
}
