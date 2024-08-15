package vn.edu.giadinh.tasksmanagement.daos;

import java.sql.SQLException;
import java.util.List;

// DAO
public interface DBHandler<T, K> {
    T get(K key) throws SQLException;
    List<T> getAll() throws SQLException;
    void insert(T target) throws SQLException;
    void update(T target) throws SQLException;
    void delete(T target) throws SQLException;
}
