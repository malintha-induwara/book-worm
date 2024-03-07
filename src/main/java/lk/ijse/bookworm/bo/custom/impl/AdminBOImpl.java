package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.AdminBO;
import lk.ijse.bookworm.dao.DAOFactory;
import lk.ijse.bookworm.dao.custom.AdminDAO;
import lk.ijse.bookworm.dto.AdminDto;
import lk.ijse.bookworm.entity.Admin;

public class AdminBOImpl implements AdminBO {

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
    public AdminDto searchAdmin(String id) {
        Admin admin = adminDAO.search(id);
        if (admin != null){
            return new AdminDto(
                    admin.getUsername(),
                    admin.getPassword()
            );
        }
        return null;
    }
}

