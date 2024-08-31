package vn.edu.giadinh.tasksmanagement.daos;

import vn.edu.giadinh.tasksmanagement.models.Task;

import java.sql.SQLException;
import java.util.List;

public interface ITaskDBHandler extends ExistsCheckableDBHandler<Task, Integer> {
    List<Task> getByResponsibility(String responsibility) throws SQLException;
    List<Task> getByTester(String tester) throws SQLException;
    List<Task> getByUser(String username) throws SQLException;
    Task create(Task target) throws SQLException;
}
