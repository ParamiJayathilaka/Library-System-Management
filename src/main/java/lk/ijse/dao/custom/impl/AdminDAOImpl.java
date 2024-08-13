package lk.ijse.dao.custom.impl;

import jakarta.persistence.Query;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.Books;
import lk.ijse.entity.Branches;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    public static Branches branches;

    @Override
    public boolean save(Admin admin) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Admin> adminList = new ArrayList<>();

        admin.setBranches(branches);
        adminList.add(admin);
        branches.setAdminList(adminList);

        session.save(admin);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean saveSeveralObject(lk.ijse.entity.Transaction entity, User user , Books books) {
        return false;
    }

    @Override
    public boolean update(Admin admin) {
       Session session = FactoryConfiguration.getInstance().getSession();
       Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("UPDATE Admin a SET a.id = :id , a.username = :username , a.password = :password WHERE a.id = :adminId");
        query.setParameter("id" , admin.getId());
        query.setParameter("username" , admin.getUsername());
        query.setParameter("password" , admin.getPassword());
        query.setParameter("adminId" , admin.getId());
        int row = query.executeUpdate();

        transaction.commit();
        session.close();
        return row > 0;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Admin> getAll() {
       Session session = FactoryConfiguration.getInstance().getSession();
       return session.createQuery("from Admin").list();
    }
}
