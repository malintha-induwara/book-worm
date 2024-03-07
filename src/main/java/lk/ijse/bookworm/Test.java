package lk.ijse.bookworm;


import lk.ijse.bookworm.bo.BOFactory;
import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.dto.UserDto;
import lk.ijse.bookworm.entity.Admin;
import lk.ijse.bookworm.util.SessionFactoryConfig;
import org.hibernate.Session;

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


        Session session = SessionFactoryConfig.getInstance().getSession();
        Admin admin = new Admin("maxverstappen", "hello");
        boolean contains = session.contains(admin);
        System.out.println(contains);
        session.close();


    }




}

