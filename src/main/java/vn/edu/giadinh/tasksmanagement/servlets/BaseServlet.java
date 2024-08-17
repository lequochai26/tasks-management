package vn.edu.giadinh.tasksmanagement.servlets;

import vn.edu.giadinh.tasksmanagement.daos.DBHandler;
import vn.edu.giadinh.tasksmanagement.daos.UserDBHandler;
import vn.edu.giadinh.tasksmanagement.enums.UserRole;
import vn.edu.giadinh.tasksmanagement.models.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseServlet extends HttpServlet {
    // Fields:
    protected DBHandler<User, String> userDBHandler;

    // Constructors:
    public BaseServlet(){
        super();
        userDBHandler = UserDBHandler.getInstance();
    }

    // Methods:
    protected User getLoggedInUser(HttpServletRequest req) {
        // Truy xuất session (phiên làm việc)
        HttpSession session = req.getSession();

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (session.getAttribute("user") == null || !(session.getAttribute("user") instanceof String)) {
            return null;
        }

        // Trường hợp người dùng đã đăng nhập, truy xuất username từ session
        String username = (String)session.getAttribute("user");

        // Truy vấn CSDL dựa trên username
        User user = null;
        try {
            user = userDBHandler.get(username);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    protected boolean isLoggedInUserAdmin(HttpServletRequest req) {
        // Lấy thông tin người dùng đang đăng nhập
        User user = getLoggedInUser(req);

        // Nếu chưa đăng nhập, trả false
        if (user == null) {
            return false;
        }

        // Nếu đã đăng nhập, kiểm tra role
        return UserRole.ADMIN.equals(user.getRole());
    }
}
