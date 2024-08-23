package vn.edu.giadinh.tasksmanagement.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/tasks")
public class TasksServlet extends BaseServlet {
    // Constructors:
    public TasksServlet() {
        super();
    }

    // Methods:
    @Override
    protected void doGet(HttpHandler handler) throws ServletException, IOException {
        handler.forward("/WEB-INF/tasks.jsp");
    }
}
