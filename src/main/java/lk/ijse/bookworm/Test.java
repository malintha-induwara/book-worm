package lk.ijse.bookworm;


import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.AdminBO;
import lk.ijse.bookworm.bo.custom.BookBO;
import lk.ijse.bookworm.bo.custom.BranchBO;
import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.dao.DAOFactory;
import lk.ijse.bookworm.dao.custom.BookDAO;
import lk.ijse.bookworm.dao.custom.UserDAO;
import lk.ijse.bookworm.dto.AdminDto;
import lk.ijse.bookworm.dto.BookDto;
import lk.ijse.bookworm.dto.BranchDto;
import lk.ijse.bookworm.dto.UserDto;
import lk.ijse.bookworm.embeddad.BookTransactionsPK;
import lk.ijse.bookworm.entity.*;
import lk.ijse.bookworm.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {


//        AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);
//        AdminDto admin = new AdminDto( "kasun", "1234");
//        System.out.println(adminBO.saveAdmin(admin));
//
//
//        BranchBO branchBO = (BranchBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRANCH);
//        BranchDto branchDto = new BranchDto("B001", "Panadura", "Panadura", "kasun");
//        System.out.println(branchBO.saveBranch(branchDto));
//
//
//        BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);
//        BookDto dto = new BookDto("B003", "Kasun", "Horror", "The Ring",true, "B001");
//        System.out.println(bookBO.saveBook(dto));


        UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
//
//        User user = userDAO.search("U001");
//
        BookDAO bookDAO = (BookDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BOOK);
//
//        Book book = bookDAO.search("B003");

//        BookTransactionsPK bookTransactionsPK = new BookTransactionsPK();
//        bookTransactionsPK.setBookId(book.getBookID());
//        bookTransactionsPK.setUserId(user.getEmail());
//
//        BookTransactions bookTransactions = new BookTransactions(book, user);
//        bookTransactions.setBookTransactionsPK(bookTransactionsPK);
//
//
//        User user = new User("U001", "Kasun", "Galle","123");
//
//        userDAO.save(user);


        User user = userDAO.search("U001");
        Book book = bookDAO.search("B003");

        System.out.println(user.getAddress());
        System.out.println(book.getAuthor());


        BookTransactions bookTransactions = new BookTransactions(book,user);


        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(bookTransactions);
        transaction.commit();
        session.close();










    }




}

