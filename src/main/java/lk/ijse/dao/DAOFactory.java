package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
        USER,TRANSACTION,BRANCHES,ADMIN,BOOK
    }
    public SuperDAO DAOTypes(DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDAOImpl();
            case TRANSACTION:
                return new TransactionDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case ADMIN:
                return new AdminDAOImpl();
            case BRANCHES:
                return new BranchesDAOImpl();
            default:
                return null;
        }
    }
}
