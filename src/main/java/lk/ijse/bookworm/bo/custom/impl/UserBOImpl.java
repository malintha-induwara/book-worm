package lk.ijse.bookworm.bo.custom.impl;

import javafx.scene.shape.Circle;
import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.dao.DAOFactory;
import lk.ijse.bookworm.dao.custom.UserDAO;
import lk.ijse.bookworm.dto.UserDto;
import lk.ijse.bookworm.entity.User;
import lk.ijse.bookworm.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {


    public static Circle circleImg;

    public static User loggedUser;


    private Session session;

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public List<UserDto> getAllUsers() {
        session = SessionFactoryConfig.getInstance().getSession();
        userDAO.setSession(session);
        List<UserDto> userDtoList = new ArrayList<>();
        try{
            for (User user : userDAO.getAll()) {
                userDtoList.add(new UserDto(user.getEmail(),
                        user.getName(),
                        user.getAddress(),
                        user.getPassword(), user.getImgUrl()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userDtoList;
    }

    @Override
    public boolean saveUser(UserDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            userDAO.setSession(session);
            userDAO.save(new User(dto.getEmail(),dto.getName(),dto.getAddress(),dto.getPassword(),dto.getImgUrl()));
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateUser(UserDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userDAO.setSession(session);
            User user = userDAO.search(dto.getEmail());
            //To clear the session
            session.clear();

            userDAO.update(new User(dto.getEmail(), dto.getName(), dto.getAddress(), dto.getPassword(), user.getImgUrl()));
            transaction.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateUserAndImg(UserDto dto) {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            userDAO.setSession(session);
            User user = userDAO.search(UserBOImpl.loggedUser.getEmail());
            session.clear();

            if (!(user.getEmail().equals(dto.getEmail()))){
                int result=userDAO.updateUserEmail(dto.getEmail(),user.getEmail());
                if (!(result>0)){
                    throw new Exception("Something went wrong");
                }
            }
            userDAO.update(new User(dto.getEmail(),dto.getName(),dto.getAddress(),dto.getPassword(),dto.getImgUrl()));
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteUser(String id) {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            userDAO.setSession(session);
            User user = userDAO.search(id);
            userDAO.delete(user);
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
    public UserDto searchUser(String id) {
        session=SessionFactoryConfig.getInstance().getSession();
        userDAO.setSession(session);
        try {
            User user = userDAO.search(id);
            if (user != null) {
                return new UserDto(user.getEmail(),
                        user.getName(),
                        user.getAddress(),
                        user.getPassword(), user.getImgUrl());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean isUserExist(UserDto userDto) {
        session=SessionFactoryConfig.getInstance().getSession();
        userDAO.setSession(session);
        try {
            User search = userDAO.search(userDto.getEmail());
            if (search != null) {
                if (search.getPassword().equals(userDto.getPassword())) {
                    loggedUser = search;
                    return true;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<UserDto> getUsersWithOverdueBooks() {
        session=SessionFactoryConfig.getInstance().getSession();
        userDAO.setSession(session);
        List<UserDto> userDtoList = new ArrayList<>();
        try {
            for (User user : userDAO.getUsersWithOverdueBooks()) {
                userDtoList.add(new UserDto(user.getEmail(),
                        user.getName(),
                        user.getAddress(),
                        user.getPassword(), user.getImgUrl()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userDtoList;
    }
}

