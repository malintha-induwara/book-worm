package lk.ijse.bookworm.dao.custom.impl;

import lk.ijse.bookworm.dao.custom.BranchDAO;
import lk.ijse.bookworm.entity.Branch;
import lk.ijse.bookworm.entity.User;
import lk.ijse.bookworm.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BranchDAOImpl implements BranchDAO {
    @Override
    public List<Branch> getAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        String sql = "FROM Branch ";
        Query query = session.createQuery(sql);
        List<Branch> branchList = query.list();
        session.close();
        return branchList;
    }

    @Override
    public boolean save(Branch entity) {
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
    public boolean update(Branch entity) {
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
            Branch branch = session.get(Branch.class, id);
            session.delete(branch);
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
    public Branch search(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
            Branch branch= session.get(Branch.class, id);
            return branch;
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }
}

