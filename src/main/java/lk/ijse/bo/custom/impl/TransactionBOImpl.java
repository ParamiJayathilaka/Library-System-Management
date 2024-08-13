package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.TransactionDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.Books;
import lk.ijse.entity.Transaction;
import lk.ijse.entity.User;

import java.util.ArrayList;
import java.util.List;

public class TransactionBOImpl implements TransactionBO {

    TransactionDAO transactionDAO = (TransactionDAO) DAOFactory.getDaoFactory().DAOTypes(DAOFactory.DAOTypes.TRANSACTION);
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().DAOTypes(DAOFactory.DAOTypes.USER);
    BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().DAOTypes(DAOFactory.DAOTypes.BOOK);
    @Override
    public boolean saveTransaction(TransactionDTO transactionDTO, UserDTO userDTO , BookDTO bookDTO) {
        return transactionDAO.saveSeveralObject(new Transaction(transactionDTO.getId(),transactionDTO.getBookTitle(),transactionDTO.getNameOfUser(),transactionDTO.getTime(),transactionDTO.getStartDate(),transactionDTO.getReturnDate()),
                new User(userDTO.getId(),userDTO.getName(),userDTO.getEmail(),userDTO.getBranch(),userDTO.getPassword()),
                new Books(bookDTO.getId(),bookDTO.getTitle(),bookDTO.getAuthor(),bookDTO.getGenre(),bookDTO.getAvailabilityStatus()));
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> userList = userDAO.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : userList){
            userDTOS.add(new UserDTO(user.getId(),user.getName(),user.getEmail(),user.getBranchName(),user.getPasswords()));
        }
        return userDTOS;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Books> booksList = bookDAO.getAll();
        List<BookDTO> bookDTOS = new ArrayList<>();

        for (Books book : booksList){
            bookDTOS.add(new BookDTO(book.getId(),book.getTitle(),book.getAuthor(),book.getTitle(),book.getGenre(),book.getAvailabilityStatus()));
        }
        return bookDTOS;
    }
}
