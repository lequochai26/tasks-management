package vn.edu.giadinh.tasksmanagement.models;

import lombok.*;
import lombok.experimental.ExtensionMethod;
import vn.edu.giadinh.tasksmanagement.enums.TaskProgress;
import vn.edu.giadinh.tasksmanagement.enums.TaskStatus;
import vn.edu.giadinh.tasksmanagement.enums.UserRole;
import vn.edu.giadinh.tasksmanagement.utils.Extension;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ExtensionMethod(Extension.class)
public class Task {
    // Fields:
    private int id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskProgress progress;
    private String responsibility;
    private String tester;

    // Methods:
    public boolean canAccess(User user) {
        if (user == null) {
            return false;
        }

        return (
                UserRole.ADMIN.equals(user.getRole())
                || (
                        !this.responsibility.isNullOrEmpty()
                        && this.responsibility.equals(user.getUsername()))
                        && UserRole.DEVELOPER.equals(user.getRole())
                || (
                        !this.tester.isNullOrEmpty()
                        && this.tester.equals(user.getUsername())
                        && UserRole.QUALITY_CONTROL.equals(user.getRole())
                        )
                );
    }
}
