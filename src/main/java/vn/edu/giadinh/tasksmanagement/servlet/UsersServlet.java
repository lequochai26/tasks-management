package vn.edu.giadinh.tasksmanagement.servlet;

import vn.edu.giadinh.tasksmanagement.enums.UserRole;
import vn.edu.giadinh.tasksmanagement.models.User;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet ("/users")
public class UsersServlet extends BaseServlet {
    // Constructors:
    public UsersServlet() {
        super();
    }

    // Methods:

    @Override
    protected void doGet(HttpHandler handler) throws Exception {
        // No permission case
        if (!hasPermission(handler, UserRole.ADMIN)) {
            handler.addMessage("Bạn không có quyền truy cập vào trang này!");
            handler.forward("/login");
            return;
        }

        // Get keyword
        String keyword = handler.getParameter("keyword");

        // Search users
        List<User> users = userDBHandler.search(keyword);

        // Put users to request
        handler.putRequest("users", users);

        // Forward to users.jsp page
        handler.forward("/WEB-INF/users.jsp");
    }
}
