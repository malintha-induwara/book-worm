package lk.ijse.bookworm;

import lk.ijse.bookworm.entity.Book;
import lk.ijse.bookworm.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Test {
    public static void main(String[] args) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Book book = new Book();
        book.setBookID("B001");
        book.setTitle("Harry Potter");
        book.setAuthor("J.K. Rowling");
        book.setGenre("Fantasy");
        book.setAvailable(true);


        session.save(book);
        transaction.commit();
        session.close();


    }
}

