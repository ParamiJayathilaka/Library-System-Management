package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BookBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Books;

import java.util.*;

public class BookBOImpl implements BookBO {

    BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().DAOTypes(DAOFactory.DAOTypes.BOOK);

    @Override
    public boolean saveBook(BookDTO book) {
       return bookDAO.save(new Books(book.getId(),book.getTitle(),book.getAuthor(),book.getGenre(),book.getAvailabilityStatus(), book.getImage()));
    }

    @Override
    public boolean deleteBook(long id) {
        return bookDAO.delete(id);
    }

    @Override
    public List<BookDTO> getAllBook() {
        List<Books> bookList = bookDAO.getAll();
        List<BookDTO> bookDTOS = new ArrayList<>();

        for (Books book : bookList){
            bookDTOS.add(new BookDTO(book.getId(),book.getTitle(),book.getAuthor(),book.getGenre(),book.getAvailabilityStatus(),book.getImage()));
        }
        return bookDTOS;
    }

    @Override
    public boolean updateBook(BookDTO bookDTO) {
        return bookDAO.update(new Books(bookDTO.getId(),bookDTO.getTitle(),bookDTO.getAuthor(),bookDTO.getGenre(),bookDTO.getAvailabilityStatus(),bookDTO.getImage()));
    }
}
