package lk.ijse.bookworm.dao;


import java.util.List;

public interface CrudDAO <T> extends SuperDAO{

    List<T> getAll() ;
    boolean save(T entity)  ;
    boolean update(T entity)  ;
    boolean delete(String id)  ;
    T search(String id) ;

}
