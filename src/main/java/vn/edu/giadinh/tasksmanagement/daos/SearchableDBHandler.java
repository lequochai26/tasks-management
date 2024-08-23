package vn.edu.giadinh.tasksmanagement.daos;

import java.sql.SQLException;
import java.util.List;

public interface SearchableDBHandler<T, K> extends DBHandler<T, K> {
    List<T> search(String keyword) throws SQLException;
}
