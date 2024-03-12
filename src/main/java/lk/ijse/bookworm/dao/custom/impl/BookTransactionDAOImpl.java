package lk.ijse.bookworm.dao.custom.impl;

import lk.ijse.bookworm.dao.custom.BookTransactionDAO;
import lk.ijse.bookworm.entity.Book;
import lk.ijse.bookworm.entity.BookTransactions;
import lk.ijse.bookworm.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class BookTransactionDAOImpl implements BookTransactionDAO {
    @Override
    public List<BookTransactions> getAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        String hql = "FROM BookTransactions ";
        Query query = session.createQuery(hql);
        List<BookTransactions> bookTransactionsList = query.list();
        session.close();
        return bookTransactionsList;
    }

    @Override
    public boolean save(BookTransactions entity) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            //Save in the book Transaction Table
            session.save(entity);
            //Update the Book Table
            session.update(entity.getBook());
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
            //Update the Book Transaction Table
            session.update(entity);
            //Update the Book Table
            session.update(entity.getBook());
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

            BookTransactions bookTransactions = session.get(BookTransactions.class, Integer.parseInt(id));
            //Update the Book Table
            Book book = bookTransactions.getBook();
            book.setAvailable(true);

            session.update(book);

            //Delete from the Book Transaction Table
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
            BookTransactions bookTransactions = session.get(BookTransactions.class, Integer.parseInt(id));
            return bookTransactions;
        }
        catch (Exception e) {
            return null;
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<BookTransactions> getAllLateBookDetails() {
        LocalDate currentDate = LocalDate.now();
        Session session = SessionFactoryConfig.getInstance().getSession();
        String hql = "FROM BookTransactions bt WHERE bt.isReturned = false AND bt.returnDate < :currentDate";
        Query query = session.createQuery(hql);
        query.setParameter("currentDate", currentDate);
        List<BookTransactions> bookTransactionsList = query.list();
        session.close();
        return bookTransactionsList;
    }
}

