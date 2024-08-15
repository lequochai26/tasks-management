package vn.edu.giadinh.tasksmanagement.utils;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // Static fields:
    private static String url = "jdbc:mysql://localhost:3306/tasksmanagement";
    private static String username = "root";
    private static String password = "Abc123456";

    // Static methods:
    public static Connection connect(String url, String username, String password) throws SQLException {
        // Khởi tạo driver
        Driver driver = new Driver();

        // Đăng ký driver
        DriverManager.registerDriver(driver);

        // Tạo kết nối và trả về
        return DriverManager.getConnection(url, username, password);
    }

    public static Connection connect() throws SQLException {
        return connect(url, username, password);
    }

    public static void executeUpdate(DBExecutor executor) throws SQLException {
        // Kết nối CSDL
        Connection connection = connect();

        // Gọi executor
        try {
            executor.execute(connection);
        }
        catch (SQLException e) {
            connection.close();
            throw e;
        }

        // Đóng kết nối CSDL
        connection.close();
    }

    public static <T> T executeQuery(DBQuery<T> query) throws SQLException {
        // Kết nối CSDL
        Connection connection = connect();

        // Thực thi truy vấn
        T result = null;
        try {
            result = query.query(connection);
        }
        catch (SQLException e) {
            connection.close();
            throw e;
        }

        // Đóng kết nối CSDL
        connection.close();

        // Trả ra kết quả truy vấn
        return result;
    }

    // Constructor:
    private DBUtil() {}
}
