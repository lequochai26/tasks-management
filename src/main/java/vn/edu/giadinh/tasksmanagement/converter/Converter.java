package vn.edu.giadinh.tasksmanagement.converter;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Converter<T> {
    T convert(ResultSet table) throws SQLException;
}
