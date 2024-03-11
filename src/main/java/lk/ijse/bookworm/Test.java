package lk.ijse.bookworm;


import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.BookBO;
import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.dao.DAOFactory;
import lk.ijse.bookworm.dao.custom.BookDAO;
import lk.ijse.bookworm.dto.BookDto;
import lk.ijse.bookworm.dto.UserDto;
import lk.ijse.bookworm.entity.Admin;
import lk.ijse.bookworm.entity.Branch;
import lk.ijse.bookworm.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

//
//        UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
////
//        boolean wadeharida = userBO.saveUser();

//        System.out.println(wadeharida);

      //  System.out.println(userBO.searchUser("U001"));

//
//        UserDto userDto = new UserDto("helsssslo@gmail.com", "Kasun", "Panadura", "1234");
//
//        boolean b = userBO.updateUser(userDto);
//        System.out.println(b);

//
//        for (UserDto allUser : userBO.getAllUsers()) {
//            System.out.println(allUser);
//        }


//        boolean deleted = userBO.deleteUser("U001");
//        System.out.println(deleted);


//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        Branch branch = session.get(Branch.class, "bras");
//        System.out.println(branch.getBranchAddress());
//        session.delete(branch);
//
//        transaction.commit();
//
//        session.close();

        BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);
//        BookDto dto = new BookDto("B003", "Kasun", "Horror", "The Ring",true, "branch");
//        bookBO.saveBook(dto);

//
//        BookDto dto1 = new BookDto("B001", "Kasun", "Horror", "The Mouse", "branch");
//        bookBO.updateBook(dto1);
//




        BookDAO bookDAO = (BookDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BOOK);

        System.out.println(bookDAO.delete("B001"));


    }




}

