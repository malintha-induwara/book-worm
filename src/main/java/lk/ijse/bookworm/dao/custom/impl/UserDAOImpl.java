package lk.ijse.bookworm.dao.custom.impl;

import lk.ijse.bookworm.bo.custom.impl.UserBOImpl;
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
        String hql = "FROM User";
        Query query = session.createQuery(hql);
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
            User user = search(UserBOImpl.loggedUser.getEmail());
            if (!(user.getEmail().equals(entity.getEmail()))){
                String hql ="UPDATE User set email = :email WHERE email = :oldEmail";
                Query query = session.createQuery(hql);
                query.setParameter("email", entity.getEmail());
                query.setParameter("oldEmail", user.getEmail());
                int result = query.executeUpdate();
                if (!(result>0)){
                    throw new Exception("Something went wrong");
                }
            }
            session.update(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
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
            User user = session.get(User.class, id);
            session.delete(user);
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

    @Override
    public List<User> getUsersWithOverdueBooks() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        String hql = "SELECT u FROM User u JOIN u.bookTransactions t WHERE t.returnDate < CURRENT_DATE AND t.isReturned = false";
        Query query = session.createQuery(hql);
        List<User> users = query.list();
        session.close();
        return users;
    }
}

