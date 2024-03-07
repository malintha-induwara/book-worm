package lk.ijse.bookworm.dao;

import lk.ijse.bookworm.dao.custom.impl.AdminDAOImpl;
import lk.ijse.bookworm.dao.custom.impl.BranchDAOImpl;
import lk.ijse.bookworm.dao.custom.impl.UserDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER,ADMIN,BRANCH
    }

    public SuperDAO  getDAO(DAOTypes types) {
        switch (types) {
            case USER:
                return  new UserDAOImpl();
            case ADMIN:
                return new AdminDAOImpl();
            case BRANCH:
                return new BranchDAOImpl();
            default:
                return null;
        }
    }

}

