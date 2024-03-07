package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.AdminBO;
import lk.ijse.bookworm.dao.DAOFactory;
import lk.ijse.bookworm.dao.custom.AdminDAO;
import lk.ijse.bookworm.dto.AdminDto;
import lk.ijse.bookworm.entity.Admin;

public class AdminBOImpl implements AdminBO {


    public static String userName;

    AdminDAO adminDAO =(AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ADMIN);


    @Override
    public boolean saveAdmin(AdminDto dto) {
        return adminDAO.save(new Admin(dto.getUsername(),dto.getPassword()));
    }

    @Override
    public boolean updateAdmin(AdminDto dto) {
        return adminDAO.update(new Admin(dto.getUsername(),dto.getPassword()));
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
                userName = dto.getUsername();
                return true;
            }
        }
        return false;
    }


}

