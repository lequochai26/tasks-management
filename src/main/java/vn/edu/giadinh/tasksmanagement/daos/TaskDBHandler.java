package vn.edu.giadinh.tasksmanagement.daos;

import lombok.experimental.ExtensionMethod;
import vn.edu.giadinh.tasksmanagement.converter.TaskConverter;
import vn.edu.giadinh.tasksmanagement.converter.TasksListConverter;
import vn.edu.giadinh.tasksmanagement.models.Task;
import vn.edu.giadinh.tasksmanagement.utils.DBUtil;
import vn.edu.giadinh.tasksmanagement.utils.Extension;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ExtensionMethod(Extension.class)
public class TaskDBHandler implements ITaskDBHandler {
    // Static fields:
    public static final String GET_SQL = "SELECT * FROM task" +
            " WHERE id=?";
    public static final String GET_ALL_SQL = "SELECT * FROM task";
    public static final String INSERT_SQL = "INSERT INTO task(title, description, status, progress, responsibility, tester)" +
            " VALUES(?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_SQL = "UPDATE task" +
            " SET title=?, description=?, status=?, progress=?, responsibility=?, tester=?" +
            " WHERE id=?";
    public static final String DELETE_SQL = "DELETE FROM task" +
            " WHERE id=?";
    public static final String SEARCH_SQL = "SELECT * FROM task" +
            " WHERE id=? OR title LIKE ?";
    public static final String EXISTS_SQL = "SELECT COUNT(*) FROM task" +
            " WHERE id=?";
    public static final String GET_BY_RESPONSIBILITY_SQL = "SELECT * FROM task" +
            " WHERE responsibility=?";
    public static final String GET_BY_TESTER_SQL = "SELECT * FROM task" +
            " WHERE tester=?";
    public static final String GET_BY_USER_SQL = "SELECT * FROM task" +
            " WHERE tester=? OR responsibility=?";

    private static TaskDBHandler instance;

    // Static methods:
    public static TaskDBHandler getInstance() {
        if (instance == null) {
            instance = new TaskDBHandler();
        }

        return instance;
    }

    // Constructors:
    private TaskDBHandler() {

    }

    // Methods:
    @Override
    public Task get(Integer key) throws SQLException {
        if (key == null) {
            return null;
        }

        return DBUtil.executeQuery(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(GET_SQL);
                    statement.setInt(1, key);

                    ResultSet table = statement.executeQuery();

                    if (!table.next()) {
                        return null;
                    }

                    return TaskConverter.getInstance()
                            .convert(table);
                }
        );
    }

    @Override
    public List<Task> getAll() throws SQLException {
        return DBUtil.executeQuery(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(GET_ALL_SQL);

                    ResultSet table = statement.executeQuery();

                    return TasksListConverter.getInstance()
                            .convert(table);
                }
        );
    }

    @Override
    public void insert(Task target) throws SQLException {
        DBUtil.executeUpdate(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
                    statement.setString(1, target.getTitle());
                    statement.setString(2, target.getDescription());
                    statement.setString(3, target.getStatus().name());
                    statement.setString(4, target.getProgress().name());
                    statement.setString(5, target.getResponsibility());
                    statement.setString(6, target.getTester());

                    statement.executeUpdate();
                }
        );
    }

    @Override
    public void update(Task target) throws SQLException {
        DBUtil.executeUpdate(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
                    statement.setString(1, target.getTitle());
                    statement.setString(2, target.getDescription());
                    statement.setString(3, target.getStatus().name());
                    statement.setString(4, target.getProgress().name());
                    statement.setString(5, target.getResponsibility());
                    statement.setString(6, target.getTester());
                    statement.setInt(7, target.getId());

                    statement.executeUpdate();
                }
        );
    }

    @Override
    public void delete(Task target) throws SQLException {
        DBUtil.executeUpdate(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
                    statement.setInt(1, target.getId());

                    statement.executeUpdate();
                }
        );
    }

    @Override
    public List<Task> search(String keyword) throws SQLException {
        if (keyword.isNullOrEmpty()) {
            return getAll();
        }

        return DBUtil.executeQuery(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(SEARCH_SQL);

                    if (keyword.canConvertToInt()) {
                        statement.setInt(1, Integer.parseInt(keyword));
                    }
                    else {
                        statement.setInt(1, -1);
                    }

                    statement.setString(2, "%" + keyword + "%");

                    ResultSet table = statement.executeQuery();

                    return TasksListConverter.getInstance()
                            .convert(table);
                }
        );
    }

    @Override
    public boolean exists(Integer key) throws SQLException {
        if (key == null) {
            return false;
        }

        return DBUtil.executeQuery(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(EXISTS_SQL);
                    statement.setInt(1, key);

                    ResultSet table = statement.executeQuery();

                    return table.next() && table.getInt(1) > 0;
                }
        );
    }

    @Override
    public List<Task> getByResponsibility(String responsibility) throws SQLException {
        return DBUtil.executeQuery(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(GET_BY_RESPONSIBILITY_SQL);
                    statement.setString(1, responsibility);

                    ResultSet table = statement.executeQuery();

                    return TasksListConverter.getInstance()
                            .convert(table);
                }
        );
    }

    @Override
    public List<Task> getByTester(String tester) throws SQLException {
        return DBUtil.executeQuery(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(GET_BY_TESTER_SQL);
                    statement.setString(1, tester);

                    ResultSet table = statement.executeQuery();

                    return TasksListConverter.getInstance()
                            .convert(table);
                }
        );
    }

    @Override
    public List<Task> getByUser(String username) throws SQLException {
        return DBUtil.executeQuery(
                connection -> {
                    PreparedStatement statement = connection.prepareStatement(GET_BY_USER_SQL);
                    statement.setString(1, username);
                    statement.setString(2, username);

                    ResultSet table = statement.executeQuery();

                    return TasksListConverter.getInstance()
                            .convert(table);
                }
        );
    }
}
