package vn.edu.giadinh.tasksmanagement.servlet;

import lombok.experimental.ExtensionMethod;
import vn.edu.giadinh.tasksmanagement.Constants;
import vn.edu.giadinh.tasksmanagement.enums.TaskProgress;
import vn.edu.giadinh.tasksmanagement.enums.TaskStatus;
import vn.edu.giadinh.tasksmanagement.enums.UserRole;
import vn.edu.giadinh.tasksmanagement.models.Task;
import vn.edu.giadinh.tasksmanagement.models.User;
import vn.edu.giadinh.tasksmanagement.utils.Extension;

import javax.servlet.annotation.WebServlet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/task-detail")
@ExtensionMethod(Extension.class)
public class TaskDetailServlet extends ActionBaseServlet {
    // Static fields:
    public static final String VIEW_NAME = "task-detail.jsp";
    public static final String LIST_VIEW = "/tasks";

    // Constructors:
    public TaskDetailServlet() {
        super();
    }

    // Methods:
    public void view(HttpHandler handler, String id) throws Exception {
        // Login validation
        if (!validateLogin(handler, Constants.MSG_ACCESS_DENIED)) {
            return;
        }

        // Get and put task progress values
        handler.putRequest("taskProgresses", TaskProgress.allValues);

        // Get users
        List<User> users = userDBHandler.getAll();
        if (!users.isNullOrEmpty()) {
            Map<String, List<User>> groupedUsers = users.stream()
                    .collect(
                            Collectors.groupingBy(
                                    u -> u.getRole().name(),
                                    Collectors.toList()
                            )
                    );

            handler.putRequest("users", groupedUsers);
        }

        // Create task case
        if (id.isNullOrEmpty()) {
            viewCreate(handler);
        }
        // View detail task case
        else {
            viewDetail(handler);
        }
    }

    private void viewCreate(HttpHandler handler) throws Exception {
        // Admin validation
        if (!validatePermission(handler, UserRole.ADMIN, Constants.MSG_ACCESS_DENIED)) {
            return;
        }

        // Show view
        showView(handler, VIEW_NAME);
    }

    private void viewDetail(HttpHandler handler) throws Exception {
        // Id validaton
        String id = handler.getParameter("id");
        if (!id.canConvertToInt()) {
            handler.addMessage(Constants.MSG_TASK_ID_INVALID);
            handler.forward(LIST_VIEW);
            return;
        }

        // Task exists validation
        Task task = taskDBHandler.get(Integer.parseInt(id));

        // User validation
        User user = getLoggedInUser(handler);

        // Role, responsibility, tester validation
        if (!task.canAccess(user)) {
            handler.addMessage(Constants.MSG_ACCESS_DENIED);
            handler.forward(LIST_VIEW);
            return;
        }

        // Put request
        handler.putRequest("task", task);

        // Show view
        showView(handler, VIEW_NAME);
    }

    @PostDetaultAction
    public void save(HttpHandler handler) throws Exception {
        // Login validation
        if (!validateLogin(handler, Constants.MSG_ACCESS_DENIED)) {
            return;
        }

        // Get id parameter
        String id = handler.getParameter("id");

        // Validation
        // Create case
        if (id.isNullOrEmpty()) {
            if (!validatePermission(handler, UserRole.ADMIN, Constants.MSG_ACCESS_DENIED)) {
                return;
            }

            create(handler);
        }
        // Update case
        else if (id.canConvertToInt()) {
            Task task = taskDBHandler.get(Integer.parseInt(id));

            if (task == null) {
                handler.addMessage(Constants.MSG_TASK_NOT_EXIST);
                view(handler);
                return;
            }

            User user = getLoggedInUser(handler);

            if (!task.canAccess(user)) {
                handler.addMessage(Constants.MSG_ACCESS_DENIED);
                view(handler);
                return;
            }

            update(handler, task);
        }
        // Invalid id case
        else {
            handler.addMessage(Constants.MSG_TASK_ID_INVALID);
            view(handler);
        }
    }

    private void create(HttpHandler handler) throws Exception {
        // Get and validate parameters
        String title = handler.getParameter("title");
        String description = handler.getParameter("description");
        String responsibility = handler.getParameter("responsibility");
        String tester = handler.getParameter("tester");
        String progressStr = handler.getParameter("progress");
        TaskProgress progress = null;

        if (title.isNullOrEmpty()) {
            handler.addMessage(Constants.MSG_INFORMATION_REQUIRED);
            view(handler);
            return;
        }

        if (!responsibility.isNullOrEmpty() && !userDBHandler.exists(responsibility)) {
            handler.addMessage(Constants.MSG_USER_NOT_FOUND);
            view(handler);
            return;
        }

        if (!tester.isNullOrEmpty() && !userDBHandler.exists(tester)) {
            handler.addMessage(Constants.MSG_USER_NOT_FOUND);
            view(handler);
            return;
        }

        if (!progressStr.isNullOrEmpty() && TaskProgress.from(progressStr) != null) {
            progress = TaskProgress.from(progressStr);
        }

        // Build new task
        Task task = Task.builder()
                .title(title)
                .status(TaskStatus.NEW)
                .progress(progress)
                .description(description)
                .responsibility(!responsibility.isNullOrEmpty() ? responsibility : null)
                .tester(!tester.isNullOrEmpty() ? tester : null)
                .build();

        // Create task
        Task createdTask = taskDBHandler.create(task);

        // Add message
        handler.addMessage("Tạo công việc thành công!");

        // Visit view
        view(handler, "" + createdTask.getId());
    }

    private void update(HttpHandler handler, Task task) throws Exception {
        // Get and update properties for task
        String title = handler.getParameter("title");
        if (!title.isNullOrEmpty()) {
            task.setTitle(title);
        }

        String progressStr = handler.getParameter("progress");
        if (!progressStr.isNullOrEmpty() && TaskProgress.from(progressStr) != null) {
            task.setProgress(TaskProgress.from(progressStr));
        }

        String responsibility = handler.getParameter("responsibility");
        if (!responsibility.isNullOrEmpty() && userDBHandler.exists(responsibility)) {
            task.setResponsibility(responsibility);
        }

        String tester = handler.getParameter("tester");
        if (!tester.isNullOrEmpty() && userDBHandler.exists(tester)) {
            task.setTester(tester);
        }

        String description = handler.getParameter("description");
        if (!description.isNullOrEmpty()) {
            task.setDescription(description);
        }

        taskDBHandler.update(task);

        handler.addMessage("Lưu công việc thành công!");

        view(handler, "" + task.getId());
    }

    @GetAction("changeStatus")
    public void changeStatus(HttpHandler handler) throws Exception {
        // Get id
        String id = handler.getParameter("id");

        if (id.isNullOrEmpty() || !id.canConvertToInt()) {
            handler.addMessage(Constants.MSG_TASK_ID_INVALID);
            showView(handler, LIST_VIEW);
            return;
        }

        // Get task
        Task task = taskDBHandler.get(Integer.parseInt(id));

        if (task == null) {
            handler.addMessage(Constants.MSG_TASK_NOT_EXIST);
            showView(handler, LIST_VIEW);
            return;
        }

        // Get status
        String statusStr = handler.getParameter("status");

        if (statusStr.isNullOrEmpty() || TaskStatus.from(statusStr) == null) {
            handler.addMessage(Constants.MSG_TASK_STATUS_INVALID);
            showView(handler, LIST_VIEW);
            return;
        }

        // Check status
        TaskStatus status = TaskStatus.from(statusStr);

        if (status.equals(TaskStatus.NEW)) {
            handler.addMessage(Constants.MSG_TASK_STATUS_INVALID);
            visitView(handler, LIST_VIEW);
        }
        else if (status.equals(TaskStatus.PROCESSING) && !TaskStatus.NEW.equals(task.getStatus())) {
            handler.addMessage(Constants.MSG_TASK_STATUS_INVALID);
            visitView(handler, LIST_VIEW);
        }
        else if (status.equals(TaskStatus.DONE) && !TaskStatus.PROCESSING.equals(task.getStatus())) {
            handler.addMessage(Constants.MSG_TASK_STATUS_INVALID);
            visitView(handler, LIST_VIEW);
        }
        else {
            task.setStatus(status);
            taskDBHandler.update(task);
            visitView(handler, "task-detail", "id=" + task.getId());
        }
    }

    @GetDefaultAction
    public void view(HttpHandler handler) throws Exception {
        String id = handler.getParameter("id");

        view(handler, id);
    }
}
