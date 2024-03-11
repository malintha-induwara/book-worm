package lk.ijse.bookworm.entity;


import lk.ijse.bookworm.embeddad.BookTransactionsPK;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "book_transaction")
public class BookTransactions {

    @EmbeddedId
    private BookTransactionsPK bookTransactionsPK;
    @CreationTimestamp
    private Timestamp borrowDate;

    private Timestamp returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id",insertable = false,updatable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "email",insertable = false,updatable = false)
    private User user;


    public BookTransactions() {
    }

    public BookTransactions(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public BookTransactionsPK getBookTransactionsPK() {
        return bookTransactionsPK;
    }

    public void setBookTransactionsPK(BookTransactionsPK bookTransactionsPK) {
        this.bookTransactionsPK = bookTransactionsPK;
    }

    public Timestamp getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Timestamp borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BookTransactions{" +
                "bookTransactionsPK=" + bookTransactionsPK +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}

