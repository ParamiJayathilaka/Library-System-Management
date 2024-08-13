package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BookOBJBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Books;

import java.util.ArrayList;
import java.util.List;

public class BookOBJBOImpl implements BookOBJBO {

  BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().DAOTypes(DAOFactory.DAOTypes.BOOK);

    @Override
    public List<BookDTO> getAllBooks() {
        List<Books> books = bookDAO.getAll();
        List<BookDTO> bookDTOS = new ArrayList<>();

        for (Books allBook : books){
            bookDTOS.add(new BookDTO(allBook.getId(), allBook.getTitle(), allBook.getGenre(), allBook.getAuthor(), allBook.getAvailabilityStatus(), allBook.getImage()));
        }
        return bookDTOS;
    }
}
