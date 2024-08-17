package vn.edu.giadinh.tasksmanagement.servlets;

import vn.edu.giadinh.tasksmanagement.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends BaseServlet {
    // Constructors:
    public LoginServlet() {
        super();
    }

    // Methods:
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Truy xuất thông tin người dùng đang đăng nhập
        User user = getLoggedInUser(req);

        // Trường hợp đã đăng nhập
        if (user != null) {
            // Chuyển hướng đến trang chính
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // Chuyển tiếp về trang đăng nhập
        req.getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiểm tra đã đăng nhập hay chưa
        User user = getLoggedInUser(req);
        if (user != null) {
            // Chuyển hướng đến trang chính
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // Trường hợp chưa đăng nhập

        // Lấy username và password
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Kiểm tra nếu 1 trong 2 rỗng thì quay về trang đăng nhập
        if (username == null || password == null) {
            // Chuyển tiếp về trang đăng nhập
            req.getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(req, resp);
            return;
        }

        // Nếu có cung cấp username và password thì kiểm tra có khớp hay không
        // Truy xuất người dùng dựa trên username đã cung cấp
        try {
            user = userDBHandler.get(username);
        }
        catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
            return;
        }

        // Trường hợp người dùng không tồn tại
        if (user == null) {
            // Chuyển tiếp về trang đăng nhập
            req.getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(req, resp);
            return;
        }

        // Trường hợp không khớp mật khẫu
        if (!password.equals(user.getPassword())) {
            // Chuyển tiếp về trang đăng nhập
            req.getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(req, resp);
            return;
        }

        // Đăng nhập thành công, lưu username vào session
        HttpSession session = req.getSession();
        session.setAttribute("user", user.getUsername());

        // Chuyển hướng đến trang chính
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
