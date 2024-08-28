package vn.edu.giadinh.tasksmanagement.servlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/home")
public class HomeServlet extends BaseServlet {
    // Constructors:
    public HomeServlet() {
        super();
    }

    // Methods:
    @Override
    protected void doGet(HttpHandler handler) throws Exception {
        // Validate login
        if (!validateLogin(handler)) {
            return;
        }

        // Logged in case
        // Forward to home.jsp page
        handler.forward(getViewUrl("index.jsp"));
    }
}
