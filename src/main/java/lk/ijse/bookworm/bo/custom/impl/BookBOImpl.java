package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.BookBO;
import lk.ijse.bookworm.dao.DAOFactory;
import lk.ijse.bookworm.dao.custom.BookDAO;
import lk.ijse.bookworm.dao.custom.BranchDAO;
import lk.ijse.bookworm.dto.BookDto;
import lk.ijse.bookworm.entity.Book;
import lk.ijse.bookworm.entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {

    BranchDAO branchDAO = (BranchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BRANCH);
    BookDAO bookDAO = (BookDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BOOK);

    @Override
    public List<BookDto> getAllBooks() {
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : bookDAO.getAll()){
            bookDtoList.add(new BookDto(
                    book.getBookID(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getTitle(),
                    book.isAvailable(),
                    book.getBranch().getBranchID()));
        }

        return bookDtoList;
    }

    @Override
    public boolean saveBook(BookDto dto) {
        Branch branch = branchDAO.search(dto.getBranchID());
        return bookDAO.save(new Book(dto.getBookId(),dto.getAuthor(),dto.getGenre(),dto.getTitle(),dto.isAvailability(),branch));
    }

    @Override
    public boolean updateBook(BookDto dto) {
        Branch branch = branchDAO.search(dto.getBranchID());
        Book book = bookDAO.search(dto.getBookId());
        return bookDAO.update(new Book(dto.getBookId(),dto.getAuthor(),dto.getGenre(),dto.getTitle(),book.isAvailable(),branch));
    }

    @Override
    public boolean deleteBook(String id) {
        return bookDAO.delete(id);
    }

    @Override
    public BookDto searchBook(String id) {
        Book book = bookDAO.search(id);
        if (book != null){
            return new BookDto(book.getBookID(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getTitle(),
                    book.isAvailable(),
                    book.getBranch().getBranchID()
            );
        }
        return null;
    }
}

