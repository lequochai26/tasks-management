package vn.edu.giadinh.tasksmanagement.daos;

import java.sql.SQLException;

public interface ExistsCheckableDBHandler<T, K> extends SearchableDBHandler<T, K> {
    boolean exists(K key) throws SQLException;
}
