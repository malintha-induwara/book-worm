package lk.ijse.bookworm.dao;

import lk.ijse.bookworm.dao.custom.impl.UserDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER
    }

    public SuperDAO  getDAO(DAOTypes types) {
        switch (types) {
            case USER:
                return  new UserDAOImpl();
            default:
                return null;
        }
    }

}

