package lk.ijse.bookworm.dao.custom.impl;

import lk.ijse.bookworm.dao.custom.UserDAO;
import lk.ijse.bookworm.entity.User;
import lk.ijse.bookworm.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class UserDAOImpl implements UserDAO {



    @Override
    public List<User> getAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        String sql = "FROM User";
        Query query = session.createQuery(sql);
        List<User> customerList = query.list();
        session.close();
        return customerList;
    }

    @Override
    public boolean save(User entity) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(User entity)  {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id)  {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            User customer = session.get(User.class, id);
            session.delete(customer);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public User search(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();

        try {
            User user= session.get(User.class, id);
            return user;
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }
}

