package vn.edu.giadinh.tasksmanagement.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends BaseServlet {
    // Constructors:
    public HomeServlet() {
        super();
    }

    // Methods:
    @Override
    protected void doGet(HttpHandler handler) throws ServletException, IOException {
        handler.forward("/WEB-INF/index.jsp");
    }
}
