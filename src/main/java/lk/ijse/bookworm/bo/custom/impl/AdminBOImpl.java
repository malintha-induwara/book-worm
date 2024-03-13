package lk.ijse.bookworm.bo.custom.impl;

import javafx.scene.shape.Circle;
import lk.ijse.bookworm.bo.custom.AdminBO;
import lk.ijse.bookworm.dao.DAOFactory;
import lk.ijse.bookworm.dao.custom.AdminDAO;
import lk.ijse.bookworm.dto.AdminDto;
import lk.ijse.bookworm.entity.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminBOImpl implements AdminBO {


    public static Admin loggedAdmin;

    public static Circle circleImg;


    AdminDAO adminDAO =(AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ADMIN);



    @Override
    public List<AdminDto> getAllAdmin() {
        List<AdminDto> adminList = new ArrayList<>();
        for (Admin admin : adminDAO.getAll()) {
            adminList.add(new AdminDto(
                    admin.getUsername(),
                    admin.getPassword()));
        }
        return adminList;
    }


    @Override
    public boolean saveAdmin(AdminDto dto) {
        return adminDAO.save(new Admin(dto.getUsername(),dto.getPassword(), dto.getImgUrl()));
    }

    @Override
    public boolean updateAdmin(AdminDto dto) {
        return adminDAO.update(new Admin(dto.getUsername(),dto.getPassword(),dto.getImgUrl()));
    }

    @Override
    public boolean deleteAdmin(String id) {
        return adminDAO.delete(id);
    }


    @Override
    public boolean isAdminExist(AdminDto dto) {
        Admin search = adminDAO.search(dto.getUsername());
        if (search != null) {
            if (search.getPassword().equals(dto.getPassword())) {
                loggedAdmin =search;
                return true;
            }
        }
        return false;
    }

}

