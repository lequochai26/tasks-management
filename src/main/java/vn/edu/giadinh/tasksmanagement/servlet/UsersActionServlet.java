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
            handler.addMessage(Constants.MSG_INFORMATION_REQUIRED);
            view(handler);
            return;
        }
        if (UserRole.from(role) == null) {
            handler.addMessage(Constants.MSG_INCORRECT_ROLE);
            view(handler);
            return;
        }
        if (fullName.isNullOrEmpty()) {
            fullName = "New User";
        }

        // Check if username exists
        if (userDBHandler.exists(username)) {
            handler.addMessage(Constants.MSG_USER_ALREADY_EXIST);
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

    // Private methods:
    private void showUsersView(HttpHandler handler) {
        showView(handler, VIEW_NAME);
    }
}
