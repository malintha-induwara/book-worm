package lk.ijse.bookworm.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO{

    ArrayList<T> getAll() ;
    boolean save(T dto)  ;
    boolean update(T dto)  ;
    boolean delete(String id)  ;
    T search(String id) ;

}
