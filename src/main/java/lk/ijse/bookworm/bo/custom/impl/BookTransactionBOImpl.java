package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.BookTransactionBO;
import lk.ijse.bookworm.dao.DAOFactory;
import lk.ijse.bookworm.dao.custom.BookDAO;
import lk.ijse.bookworm.dao.custom.BookTransactionDAO;
import lk.ijse.bookworm.dao.custom.UserDAO;
import lk.ijse.bookworm.dto.BorrowBookDto;
import lk.ijse.bookworm.entity.Book;
import lk.ijse.bookworm.entity.BookTransactions;
import lk.ijse.bookworm.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookTransactionBOImpl implements BookTransactionBO {

    BookTransactionDAO bookTransactionDAO = (BookTransactionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BOOK_TRANSACTION);

    BookDAO bookDAO = (BookDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BOOK);

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public List<BorrowBookDto> getAllBorrowedBooks() {
        List<BorrowBookDto> borrowBookDtoList = new ArrayList<>();
        for (BookTransactions bookTransactions : bookTransactionDAO.getAll()) {
            borrowBookDtoList.add(new BorrowBookDto(
                    String.valueOf(bookTransactions.getTransactionId()),
                    bookTransactions.getUser().getEmail(),
                    bookTransactions.getBook().getBookID(),
                    String.valueOf(bookTransactions.getBorrowDate()),
                    String.valueOf(bookTransactions.getReturnDate()),
                    bookTransactions.isReturned()
            ));
        }
        return borrowBookDtoList;
    }

    @Override
    public boolean saveBorrowedBook(BorrowBookDto dto) {
        Book book = bookDAO.search(dto.getBookId());
        book.setAvailable(false);
        User user = userDAO.search(dto.getUserId());
       return bookTransactionDAO.save(new BookTransactions(book,user, LocalDate.parse(dto.getReturnDate())));
    }

    @Override
    public boolean updateBorrowedBook(String id) {
        BookTransactions transaction = bookTransactionDAO.search(id);
        Book book = transaction.getBook();
        boolean available = !(book.isAvailable());
        book.setAvailable(available);
        transaction.setBook(book);
        transaction.setReturned(!transaction.isReturned());
        return bookTransactionDAO.update(transaction);
    }

    @Override
    public boolean deleteBorrowedBook(String id) {
        return bookTransactionDAO.delete(id);
    }

    @Override
    public BorrowBookDto searchBorrowedBook(String id) {
        BookTransactions transactions = bookTransactionDAO.search(id);
        if (transactions != null) {
            return new BorrowBookDto(
                    String.valueOf(transactions.getTransactionId()),
                    transactions.getUser().getEmail(),
                    transactions.getBook().getBookID(),
                    String.valueOf(transactions.getBorrowDate()),
                    String.valueOf(transactions.getReturnDate()),
                    transactions.isReturned()
            );
        }
        return null;
    }

    @Override
    public List<BorrowBookDto> getAllLateBookDetails() {
        List<BorrowBookDto> borrowBookDtoList = new ArrayList<>();
        for (BookTransactions bookTransactions : bookTransactionDAO.getAllLateBookDetails()) {
            borrowBookDtoList.add(new BorrowBookDto(
                    String.valueOf(bookTransactions.getTransactionId()),
                    bookTransactions.getUser().getEmail(),
                    bookTransactions.getBook().getBookID(),
                    String.valueOf(bookTransactions.getBorrowDate()),
                    String.valueOf(bookTransactions.getReturnDate()),
                    bookTransactions.isReturned()
            ));
        }
        return borrowBookDtoList;
    }
}

