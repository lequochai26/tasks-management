package vn.edu.giadinh.tasksmanagement.enums;

import lombok.experimental.ExtensionMethod;
import vn.edu.giadinh.tasksmanagement.utils.Extension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExtensionMethod(Extension.class)
public enum TaskProgress {
    ANALYZING,
    DEVELOPING,
    TEST_ATTEMPTING,
    TESTING,
    TEST_FAILED,
    TEST_SUCCESS;

    // Static fields:
    public static final List<TaskProgress> allValues = Arrays.stream(TaskProgress.values()).toList();

    // Methods:
    public static TaskProgress from(String name) {
        if (name.isNullOrEmpty()) {
            return null;
        }

        return allValues.find(
                value -> value.name().equals(name)
        );
    }
}
