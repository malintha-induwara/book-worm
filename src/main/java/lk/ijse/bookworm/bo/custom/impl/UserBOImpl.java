package lk.ijse.bookworm.bo.custom.impl;

import javafx.scene.shape.Circle;
import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.dao.DAOFactory;
import lk.ijse.bookworm.dao.custom.UserDAO;
import lk.ijse.bookworm.dto.UserDto;
import lk.ijse.bookworm.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {


    public static Circle circleImg;

    public static User loggedUser;


    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userDAO.getAll()) {
            userDtoList.add(new UserDto(user.getEmail(),
                    user.getName(),
                    user.getAddress(),
                    user.getPassword(), user.getImgUrl()));
        }
        return userDtoList;
    }

    @Override
    public boolean saveUser(UserDto dto) {
        return userDAO.save(new User(dto.getEmail(),dto.getName(),dto.getAddress(),dto.getPassword(),dto.getImgUrl()));
    }

    @Override
    public boolean updateUser(UserDto dto) {
        User user = userDAO.search(dto.getEmail());
        return userDAO.update(new User(dto.getEmail(),dto.getName(),dto.getAddress(),dto.getPassword(),user.getImgUrl()));
    }

    @Override
    public boolean updateUserAndImg(UserDto dto) {
        return userDAO.update(new User(dto.getEmail(),dto.getName(),dto.getAddress(),dto.getPassword(),dto.getImgUrl()));
    }

    @Override
    public boolean deleteUser(String id) {
        return userDAO.delete(id);
    }

    @Override
    public UserDto searchUser(String id) {
        User user = userDAO.search(id);
        if (user != null){
            return new UserDto(user.getEmail(),
                    user.getName(),
                    user.getAddress(),
                    user.getPassword(),user.getImgUrl());
        }
        return null;
    }

    @Override
    public boolean isUserExist(UserDto userDto) {
        User search = userDAO.search(userDto.getEmail());
        if (search != null) {
            if (search.getPassword().equals(userDto.getPassword())) {
                loggedUser=search;
                return true;
            }
        }
        return false;
    }
}

