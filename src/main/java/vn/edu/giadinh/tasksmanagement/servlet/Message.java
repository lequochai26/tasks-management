package vn.edu.giadinh.tasksmanagement.servlet;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    // Inner classes:
    @Getter
    @AllArgsConstructor
    public enum Type {
        // Values:
        ERROR("Lỗi"),
        WARNING("Cảnh báo"),
        INFO("Thông báo");

        // Fields:
        private final String title;
    }

    // Fields:
    private String content;
    private Type type;

    // Methods:

    @Override
    public String toString() {
        return content;
    }
}
