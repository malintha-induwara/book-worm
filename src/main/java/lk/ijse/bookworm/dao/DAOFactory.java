package lk.ijse.bookworm.dao;

import lk.ijse.bookworm.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER,ADMIN,BRANCH,BOOK,BOOK_TRANSACTION
    }

    public SuperDAO  getDAO(DAOTypes types) {
        switch (types) {
            case USER:
                return  new UserDAOImpl();
            case ADMIN:
                return new AdminDAOImpl();
            case BRANCH:
                return new BranchDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case BOOK_TRANSACTION:
                return new BookTransactionDAOImpl();
            default:
                return null;
        }
    }

}

