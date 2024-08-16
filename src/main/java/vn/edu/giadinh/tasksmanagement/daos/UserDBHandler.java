package vn.edu.giadinh.tasksmanagement.daos;

import vn.edu.giadinh.tasksmanagement.converter.UserConverter;
import vn.edu.giadinh.tasksmanagement.models.User;
import vn.edu.giadinh.tasksmanagement.utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDBHandler implements DBHandler<User, String> {
    // Static fields:
    private static final String GET_SQL = "SELECT * FROM user\n" +
            " WHERE username=?";
    private static final String GET_ALL_SQL = "SELECT * FROM user";
    private static final String INSERT_SQL = "INSERT INTO user(username, password, fullName, role)\n" +
            " VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE user\n" +
            " SET password=?, fullName=?, role=?\n" +
            " WHERE username=?";
    private static final String DELETE_SQL = "DELETE user\n" +
            " WHERE username=?";

    private static UserDBHandler instance;

    // Static methods:
    public static UserDBHandler getInstance() {
        if (instance == null) {
            instance = new UserDBHandler();
        }

        return instance;
    }

    // Constructors:
    private UserDBHandler() {

    }

    // Methods:
    @Override
    public User get(String key) throws SQLException {
        return DBUtil.executeQuery(
                connection -> {
                    // Tạo lệnh SQL truy vấn
                    PreparedStatement statement = connection.prepareStatement(GET_SQL);
                    statement.setString(1, key);

                    // Thực thi truy vấn
                    ResultSet table = statement.executeQuery();

                    // Kiểm tra kết quả truy vấn có rỗng không
                    if (!table.next()) {
                        connection.close();
                        return null;
                    }

                    // Chuyển đổi bảng kết quả truy vấn sang đối tượng User
                    return UserConverter.getInstance()
                            .convert(table);
                }
        );
    }

    @Override
    public List<User> getAll() throws SQLException {
        return DBUtil.executeQuery(
                connection -> {
                    // Tạo lệnh SQL
                    PreparedStatement statement = connection.prepareStatement(GET_ALL_SQL);

                    // Thực thi truy vấn
                    ResultSet table = statement.executeQuery();

                    // Khởi tạo danh sách người dùng
                    List<User> users = new ArrayList<>();

                    // Duyệt qua từng dòng trong bảng dữ liệu trả về
                    while (table.next()) {
                        // Chuyển đổi bảng sang đối tượng User
                        User user = UserConverter.getInstance()
                                .convert(table);

                        // Thêm đói tượng User sau khi chuyển đổi vào danh sách
                        users.add(user);
                    }

                    // Trả ra danh sách người dùng
                    return users;
                }
        );
    }

    @Override
    public void insert(User target) throws SQLException {
        // Truy xuất thông tin người dùng
        String username = target.getUsername();
        String password = target.getPassword();
        String fullName = target.getFullName();
        String roleStr = target.getRole()
                .name();

        // Thực thi insert vào CSDL
        DBUtil.executeUpdate(
                connection -> {
                    // Tạo lệnh SQL
                    PreparedStatement statement = connection.prepareStatement(INSERT_SQL);

                    // Gán tham số cho các ? trong lệnh
                    statement.setString(1, username);
                    statement.setString(2, password);
                    statement.setString(3, fullName);
                    statement.setString(4, roleStr);

                    // Thực thi lệnh SQL
                    statement.executeUpdate();
                }
        );
    }

    @Override
    public void update(User target) throws SQLException {
        // Truy xuất thông tin người dùng
        String username = target.getUsername();
        String password = target.getPassword();
        String fullName = target.getFullName();
        String roleStr = target.getRole()
                .name();

        // Thực thi update vào CSDL
        DBUtil.executeUpdate(
                connection -> {
                    // Tạo lệnh SQL
                    PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);

                    // Gán tham số cho các ? trong lệnh
                    statement.setString(4, username);
                    statement.setString(1, password);
                    statement.setString(2, fullName);
                    statement.setString(3, roleStr);

                    // Thực thi lệnh SQL
                    statement.executeUpdate();
                }
        );
    }

    @Override
    public void delete(User target) throws SQLException {
        // Lấy khóa chính của user
        String username = target.getUsername();

        // Thực thi delete dưới CSDL
        DBUtil.executeUpdate(
                connection -> {
                    // Tạo lệnh SQL
                    PreparedStatement statement = connection.prepareStatement(DELETE_SQL);

                    // Gán tham số cho các ? trong lệnh
                    statement.setString(1, username);

                    // Thực thi lệnh SQL
                    statement.executeUpdate();
                }
        );
    }
}
