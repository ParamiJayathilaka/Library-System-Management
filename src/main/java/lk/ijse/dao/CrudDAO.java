package lk.ijse.dao;

import lk.ijse.entity.Books;
import lk.ijse.entity.Transaction;
import lk.ijse.entity.User;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
   boolean save(T entity);
   boolean saveSeveralObject(Transaction entity, User twoEntity , Books books);
   boolean update(T entity);
   boolean delete(long id);
   List<T> getAll();
}
