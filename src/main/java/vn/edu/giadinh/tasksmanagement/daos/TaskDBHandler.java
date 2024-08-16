package vn.edu.giadinh.tasksmanagement.daos;

import vn.edu.giadinh.tasksmanagement.converter.TaskConverter;
import vn.edu.giadinh.tasksmanagement.models.Task;
import vn.edu.giadinh.tasksmanagement.utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDBHandler implements DBHandler<Task, Integer> {
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

                    List<Task> result = new ArrayList<>();
                    while (table.next()) {
                        result.add(
                                TaskConverter.getInstance()
                                        .convert(table)
                        );
                    }

                    return result;
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
}
