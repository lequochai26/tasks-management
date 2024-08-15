package vn.edu.giadinh.tasksmanagement.converter;

import vn.edu.giadinh.tasksmanagement.enums.TaskProgress;
import vn.edu.giadinh.tasksmanagement.enums.TaskStatus;
import vn.edu.giadinh.tasksmanagement.models.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskConverter implements Converter<Task> {
    // Static fields:
    private static TaskConverter instance;

    // Static methods:
    public static TaskConverter getInstance() {
        if (instance == null) {
            instance = new TaskConverter();
        }

        return instance;
    }

    // Constructors:
    private TaskConverter() {
    }

    // Methods:
    @Override
    public Task convert(ResultSet table) throws SQLException {
        // Lấy dữ liệu của cả dòng (lấy từng ô)
        int id = table.getInt("id");
        String title = table.getString("title");
        String description = table.getString("description");
        String statusStr = table.getString("status");
        String progressStr = table.getString("progress");
        String responsibility = table.getString("responsibility");
        String tester = table.getString("tester");

        // Chuyển đổi các chuỗi sang các enums
        TaskStatus status = TaskStatus.valueOf(statusStr);
        TaskProgress progress = TaskProgress.valueOf(progressStr);

        // Tạo đối tượng Task từ dữ liệu đã có
        return Task.builder()
                .id(id)
                .title(title)
                .description(description)
                .status(status)
                .progress(progress)
                .responsibility(responsibility)
                .tester(tester)
                .build();
    }
}
