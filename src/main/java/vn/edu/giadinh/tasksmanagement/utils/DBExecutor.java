package vn.edu.giadinh.tasksmanagement.utils;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBExecutor {
    void execute(Connection connection) throws SQLException;
}
