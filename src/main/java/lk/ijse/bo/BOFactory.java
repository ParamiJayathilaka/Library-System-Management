package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        USER,SETTING,REGISTER,LOGIN,HISTORY,BOOK,ADMIN,BOOK_OBJ,BRANCH,TRANSACTION
    }

    public SuperBO BOTypes(BOTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserFormBOImpl();
            case SETTING:
                return new SettingFormBOImpl();
            case REGISTER:
                return new RegisterFormBOImpl();
            case LOGIN:
                return new LoginFormBOImpl();
            case HISTORY:
                return new HistoryBOImpl();
            case BOOK:
                return new BookBOImpl();
            case ADMIN:
                return new AdminBOImpl();
            case BRANCH:
                return new BranchBOImpl();
            case BOOK_OBJ:
                return new BookOBJBOImpl();
            case TRANSACTION:
                return new TransactionBOImpl();
            default:
                return null;
        }
    }
}
