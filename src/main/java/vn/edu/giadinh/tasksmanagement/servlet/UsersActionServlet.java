package vn.edu.giadinh.tasksmanagement.servlet;

import lombok.experimental.ExtensionMethod;
import vn.edu.giadinh.tasksmanagement.Constants;
import vn.edu.giadinh.tasksmanagement.enums.UserRole;
import vn.edu.giadinh.tasksmanagement.models.User;
import vn.edu.giadinh.tasksmanagement.utils.AuthUtil;
import vn.edu.giadinh.tasksmanagement.utils.Extension;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/users-action")
@ExtensionMethod(Extension.class)
public class UsersActionServlet extends ActionBaseServlet {
    // Static fields:
    public static final String VIEW_NAME = "users.jsp";

    // Constructors:
    public UsersActionServlet() {
        super();
    }

    // Methods:
    @GetDefaultAction
    public void view(HttpHandler handler) throws Exception {
        // No permission case
        if (
                !validatePermission(handler, UserRole.ADMIN, Constants.MSG_ACCESS_DENIED)
        ) {
            return;
        }

        // Get keyword
        String keyword = handler.getParameter("keyword");

        // Search users
        List<User> users = userDBHandler.search(keyword);

        // Put users to request
        handler.putRequest("users", users);

        // Show users view
        showUsersView(handler);
    }

    @PostAction("create")
    public void create(HttpHandler handler) throws Exception {
        // No permission case
        if (!validatePermission(handler, UserRole.ADMIN, Constants.MSG_ACCESS_DENIED)) {
            return;
        }

        // Get and validate info
        String username = handler.getParameter("username");
        String password = handler.getParameter("password");
        String fullName = handler.getParameter("fullName");
        String role = handler.getParameter("role");
        if (username.isNullOrEmpty() || password.isNullOrEmpty() || role.isNullOrEmpty()) {
            handler.addMessage(Message.Type.WARNING, Constants.MSG_INFORMATION_REQUIRED);
            view(handler);
            return;
        }
        if (UserRole.from(role) == null) {
            handler.addMessage(Message.Type.WARNING, Constants.MSG_INCORRECT_ROLE);
            view(handler);
            return;
        }
        if (fullName.isNullOrEmpty()) {
            fullName = "New User";
        }

        // Check if username exists
        if (userDBHandler.exists(username)) {
            handler.addMessage(Message.Type.WARNING, Constants.MSG_USER_ALREADY_EXIST);
            view(handler);
            return;
        }

        // Encrypt password
        password = AuthUtil.encrypt(password);

        // Create user
        User user = User.builder()
                .username(username)
                .password(password)
                .fullName(fullName)
                .role(UserRole.from(role))
                .build();

        // Save user
        userDBHandler.insert(user);

        // Fall back to users view
        view(handler);
    }

    @PostAction("update")
    public void update(HttpHandler handler) throws Exception {
        // No permission case
        if (!validatePermission(handler, UserRole.ADMIN, Constants.MSG_ACCESS_DENIED)) {
            return;
        }

        // Get and validate username
        String username = handler.getParameter("username");
        if (username.isNullOrEmpty()) {
            handler.addMessage(Message.Type.WARNING, Constants.MSG_INFORMATION_REQUIRED);
            view(handler);
            return;
        }

        // Load user from db by given username
        User user = userDBHandler.get(username);

        // Check user exists
        if (user == null) {
            handler.addMessage(Message.Type.WARNING, Constants.MSG_USER_NOT_FOUND);
            view(handler);
            return;
        }

        // Update informations
        String password = handler.getParameter("password");
        if (!password.isNullOrEmpty()) {
            user.setPassword(AuthUtil.encrypt(password));
        }

        String fullName = handler.getParameter("fullName");
        if (!fullName.isNullOrEmpty()) {
            user.setFullName(fullName);
        }

        String role = handler.getParameter("role");
        if (!role.isNullOrEmpty() && UserRole.from(role) != null) {
            user.setRole(UserRole.from(role));
        }

        // Updating user
        userDBHandler.update(user);

        // Fall back to view
        view(handler);
    }

    @GetAction("delete")
    public void delete(HttpHandler handler) throws Exception {
        // No permission case
        if (!validatePermission(handler, UserRole.ADMIN, Constants.MSG_ACCESS_DENIED)) {
            return;
        }

        // Get and validate username
        String username = handler.getParameter("username");
        if (username.isNullOrEmpty()) {
            handler.addMessage(Message.Type.WARNING, Constants.MSG_INFORMATION_REQUIRED);
            view(handler);
            return;
        }

        User user = userDBHandler.get(username);
        if (user == null) {
            handler.addMessage(Message.Type.WARNING, Constants.MSG_USER_NOT_FOUND);
            view(handler);
            return;
        }

        // Make sure performing user is not user to be deleted
        if (user.getUsername().equals(handler.retrieveSession("user", String.class))) {
            handler.addMessage(Message.Type.WARNING, Constants.MSG_SELF_DELETE);
            view(handler);
            return;
        }

        // Delete user
        userDBHandler.delete(user);

        // Fallback to view
        view(handler);
    }

    // Private methods:
    private void showUsersView(HttpHandler handler) {
        showView(handler, VIEW_NAME);
    }
}
