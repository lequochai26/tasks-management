package vn.edu.giadinh.tasksmanagement.converter;

import vn.edu.giadinh.tasksmanagement.models.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TasksListConverter implements Converter<List<Task>> {
    // Static fields:
    private static TasksListConverter instance;

    // Static methods:
    public static TasksListConverter getInstance() {
        if (instance == null) {
            instance = new TasksListConverter();
        }

        return instance;
    }

    // Fields:
    private Converter<Task> taskConverter;

    // Constructors:
    private TasksListConverter() {
        taskConverter = TaskConverter.getInstance();
    }

    @Override
    public List<Task> convert(ResultSet table) throws SQLException {
        List<Task> result = new ArrayList<>();

        while (table.next()) {
            result.add(
                    taskConverter.convert(table)
            );
        }

        return result;
    }
}
