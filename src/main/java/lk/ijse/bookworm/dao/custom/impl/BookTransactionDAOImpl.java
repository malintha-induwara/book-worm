package lk.ijse.bookworm.dao.custom.impl;

import lk.ijse.bookworm.dao.custom.BookTransactionDAO;
import lk.ijse.bookworm.entity.BookTransactions;
import lk.ijse.bookworm.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookTransactionDAOImpl implements BookTransactionDAO {
    @Override
    public List<BookTransactions> getAll() {

        Session session = SessionFactoryConfig.getInstance().getSession();
        String sql = "FROM BookTransactions ";

        Query query = session.createQuery(sql);
        List<BookTransactions> bookTransactionsList = query.list();
        session.close();
        return bookTransactionsList;
    }

    @Override
    public boolean save(BookTransactions entity) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(entity);
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(BookTransactions entity) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            transaction.rollback();
            return false;
        }
        finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            BookTransactions bookTransactions = session.get(BookTransactions.class, id);
            session.delete(bookTransactions);
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }
        finally {
            session.close();
        }
    }

    @Override
    public BookTransactions search(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();

        try {
            BookTransactions bookTransactions = session.get(BookTransactions.class, id);
            return bookTransactions;
        }
        catch (Exception e) {
            return null;
        }
        finally {
            session.close();
        }
    }
}

