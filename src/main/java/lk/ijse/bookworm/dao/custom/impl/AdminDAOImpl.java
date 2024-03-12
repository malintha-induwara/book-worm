package lk.ijse.bookworm.dao.custom.impl;

import lk.ijse.bookworm.dao.custom.AdminDAO;
import lk.ijse.bookworm.entity.Admin;
import lk.ijse.bookworm.entity.User;
import lk.ijse.bookworm.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public List<Admin> getAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        String hql = "FROM Admin";
        Query query = session.createQuery(hql);
        List<Admin> adminList = query.list();
        session.close();
        return adminList;
    }

    @Override
    public boolean save(Admin entity) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Admin entity) {
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
    public boolean delete(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Admin admin = session.get(Admin.class, id);
            session.delete(admin);
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
    public Admin search(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
            Admin admin= session.get(Admin.class, id);
            return admin;
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }

}

