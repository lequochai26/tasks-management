package vn.edu.giadinh.tasksmanagement.utils;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBQuery<T> {
    T query(Connection connection) throws SQLException;
}
