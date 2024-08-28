package vn.edu.giadinh.tasksmanagement.servlet;

import vn.edu.giadinh.tasksmanagement.Constants;
import vn.edu.giadinh.tasksmanagement.enums.UserRole;
import vn.edu.giadinh.tasksmanagement.models.User;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/users-action")
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

    // Private methods:
    private void showUsersView(HttpHandler handler) {
        showView(handler, VIEW_NAME);
    }
}
