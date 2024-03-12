package lk.ijse.bookworm.dao.custom.impl;

import lk.ijse.bookworm.dao.custom.BookDAO;
import lk.ijse.bookworm.entity.Book;
import lk.ijse.bookworm.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> getAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        String hql = "FROM Book ";

        Query query = session.createQuery(hql);
        List<Book> bookList = query.list();
        session.close();
        return bookList;
    }

    @Override
    public boolean save(Book entity) {
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
    public boolean update(Book entity) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(entity);
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
    public boolean delete(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Book book = session.get(Book.class, id);
            session.delete(book);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public Book search(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
            Book book = session.get(Book.class, id);
            return book;
        } catch (Exception e) {
            return null;
        }finally {
            session.close();
        }
    }
}

