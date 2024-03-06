package lk.ijse.bookworm.dao.custom.impl;

import lk.ijse.bookworm.dao.custom.UserDAO;
import lk.ijse.bookworm.entity.User;
import lk.ijse.bookworm.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() {
        return null;
    }

    @Override
    public boolean save(User dto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Serializable save = session.save(dto);
        transaction.commit();
        session.close();
        return save != null;
    }

    @Override
    public boolean update(User dto)  {
        return false;
    }

    @Override
    public boolean delete(String id)  {
        return false;
    }

    @Override
    public User search(String id) {
        return null;
    }
}

