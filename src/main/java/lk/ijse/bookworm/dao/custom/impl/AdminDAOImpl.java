package lk.ijse.bookworm.dao.custom.impl;

import lk.ijse.bookworm.bo.custom.impl.AdminBOImpl;
import lk.ijse.bookworm.dao.custom.AdminDAO;
import lk.ijse.bookworm.entity.Admin;
import lk.ijse.bookworm.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AdminDAOImpl implements AdminDAO {


    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
    @Override
    public List<Admin> getAll() {
        String hql = "FROM Admin";
        Query<Admin> query = session.createQuery(hql, Admin.class);
        return query.list();
    }

    @Override
    public void save(Admin entity) {
      session.save(entity);
    }

    @Override
    public void update(Admin entity) {
       session.update(entity);
    }

    @Override
    public void delete(Admin entity) {
        session.delete(entity);
    }

    @Override
    public Admin search(String id) {
        return session.get(Admin.class, id);
    }


}

