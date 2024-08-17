package vn.edu.giadinh.tasksmanagement.servlets;

import vn.edu.giadinh.tasksmanagement.daos.DBHandler;
import vn.edu.giadinh.tasksmanagement.daos.UserDBHandler;
import vn.edu.giadinh.tasksmanagement.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends BaseServlet {
    // Constructors:
    public UsersServlet(){
        super();
    }

    // Methods:
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiểm tra người dùng đang đăng nhập có quyền admin hay không
        // Trường hợp không có quyền admin
        if (!isLoggedInUserAdmin(req)) {
            resp.sendRedirect(req.getContextPath() + "/no-permission.jsp");
            return;
        }

        // Tải dữ liệu từ DB
        List<User> users = null;
        try {
            users = userDBHandler.getAll();
        }
        catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
            return;
        }

        // Lưu trữ dữ liệu hệ thống để giao diện hiển thị ra
        req.setAttribute("users", users);

        // Chuyển tiếp đến trang users.jsp
        try {
            req.getRequestDispatcher("/WEB-INF/users.jsp")
                    .forward(req, resp);
        }
        catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }
}
