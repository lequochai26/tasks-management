package vn.edu.giadinh.tasksmanagement.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum TaskStatus {
    NEW,
    PROCESSING,
    DONE;

    // Static fields:
    public static final Map<String, TaskStatus> all = Arrays.stream(TaskStatus.values())
            .collect(Collectors.toMap(TaskStatus::name, s -> s));

    // Methods:
    public static TaskStatus from(String status) {
        return all.get(status);
    }
}
