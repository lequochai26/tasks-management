package vn.edu.giadinh.tasksmanagement.servlet;

import lombok.experimental.ExtensionMethod;
import vn.edu.giadinh.tasksmanagement.Constants;
import vn.edu.giadinh.tasksmanagement.enums.UserRole;
import vn.edu.giadinh.tasksmanagement.models.Task;
import vn.edu.giadinh.tasksmanagement.models.User;
import vn.edu.giadinh.tasksmanagement.utils.Extension;

import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/tasks")
@ExtensionMethod(Extension.class)
public class TasksServlet extends ActionBaseServlet {
    // Static fields:
    public static final String VIEW_NAME = "tasks.jsp";

    // Constructors:
    public TasksServlet() {
        super();
    }

    // Methods:
    @GetDefaultAction
    public void view(HttpHandler handler) throws Exception {
        // No permission
        if (!validateLogin(handler, Constants.MSG_ACCESS_DENIED)) {
            return;
        }

        // Get logged in user
        User user = getLoggedInUser(handler);

        // Get keyword parameter
        String keyword = handler.getParameter("keyword");

        // Search tasks
        List<Task> tasks = null;
        if (UserRole.ADMIN.equals(user.getRole())) {
            tasks = taskDBHandler.search(keyword);
        }
        else {
            tasks = taskDBHandler.getByUser(user.getUsername());

            // Filter with keyword
            if (!tasks.isNullOrEmpty() && !keyword.isNullOrEmpty()) {
                tasks = tasks.stream()
                        .filter(
                                Objects::nonNull
                        )
                        .filter(
                                t -> ("" + t.getId()).contains(keyword.toLowerCase()) || (
                                        !t.getTitle().isNullOrEmpty() && t.getTitle().toLowerCase().contains(keyword.toLowerCase())
                                )
                        )
                        .collect(Collectors.toList());
            }
        }

        // Check and make sure tasks variable can't be null
        if (tasks.isNullOrEmpty()) {
            tasks = new ArrayList<>();
        }

        // Find neccessary users
        Map<String, User> users = tasks.stream()
                .filter(Objects::nonNull)
                .filter(t -> !t.getResponsibility().isNullOrEmpty() || !t.getTester().isNullOrEmpty())
                .map(t -> {
                    List<String> usernames = new ArrayList<>();

                    if (!t.getResponsibility().isNullOrEmpty()) {
                        usernames.add(t.getResponsibility());
                    }

                    if (!t.getTester().isNullOrEmpty()) {
                        usernames.add(t.getTester());
                    }

                    return usernames;
                })
                .flatMap(List::stream)
                .distinct()
                .map(username -> {
                    try {
                        return userDBHandler.get(username);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toMap(User::getUsername, u -> u));

        // Put request
        handler.putRequest("tasks", tasks);
        handler.putRequest("users", users);

        // Show view
        showView(handler, VIEW_NAME);
    }

    @GetAction("delete")
    public void delete(HttpHandler handler) throws Exception {
        // Permission validation
        if (!validatePermission(handler, UserRole.ADMIN, Constants.MSG_ACCESS_DENIED)) {
            return;
        }

        // Get task id
        String id = handler.getParameter("id");

        // Validate id
        if (id.isNullOrEmpty() || !id.canConvertToInt()) {
            handler.addMessage(Constants.MSG_TASK_ID_INVALID);
            view(handler);
            return;
        }

        int idInt = Integer.parseInt(id);

        // Validate exists
        if (!taskDBHandler.exists(idInt)) {
            handler.addMessage(Constants.MSG_TASK_NOT_EXIST);
            view(handler);
            return;
        }

        // Delete task
        taskDBHandler.delete(
                Task.builder()
                        .id(idInt)
                        .build()
        );

        // Show view
        view(handler);
    }
}
