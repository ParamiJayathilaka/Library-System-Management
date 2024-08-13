package lk.ijse.dao.custom.impl;

import jakarta.persistence.Query;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BranchesDAO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.Books;
import lk.ijse.entity.Branches;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BranchesDAOImpl implements BranchesDAO {
    @Override
    public boolean save(Branches branches) {
       Session session = FactoryConfiguration.getInstance().getSession();
       Transaction transaction = session.beginTransaction();

       AdminDAOImpl.branches = branches;
       UserDAOImpl.branches = branches;
       session.save(branches);

       transaction.commit();
       session.close();
       return true;
    }

    @Override
    public boolean saveSeveralObject(lk.ijse.entity.Transaction entity, User user , Books books) {
        return false;
    }

    @Override
    public boolean update(Branches branches) {
       Session session = FactoryConfiguration.getInstance().getSession();
       Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("UPDATE Branches b SET b.id = :id , b.branchIdNumber = :branchIdNumber , b.branchName = :branchName WHERE b.id = :branchId");
        query.setParameter("id" , branches.getId());
        query.setParameter("branchIdNumber" , branches.getBranchIdNumber());
        query.setParameter("branchName" , branches.getBranchName());

        query.setParameter("branchId" , branches.getId());

        int row = query.executeUpdate();

       transaction.commit();
       session.close();
       return row > 0;
    }

    @Override
    public boolean delete(long id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Branches branches = session.get(Branches.class,id);

        for (User user : branches.getUserList()){
            session.delete(user);
        }

        for (Admin admin : branches.getAdminList()){
            session.delete(admin);
        }
        session.delete(branches);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Branches> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        return session.createQuery("FROM Branches ").list();
    }
}
