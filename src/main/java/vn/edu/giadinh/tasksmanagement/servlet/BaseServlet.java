package vn.edu.giadinh.tasksmanagement.servlet;

import lombok.experimental.ExtensionMethod;
import vn.edu.giadinh.tasksmanagement.daos.DBHandler;
import vn.edu.giadinh.tasksmanagement.daos.SearchableDBHandler;
import vn.edu.giadinh.tasksmanagement.daos.TaskDBHandler;
import vn.edu.giadinh.tasksmanagement.daos.UserDBHandler;
import vn.edu.giadinh.tasksmanagement.enums.UserRole;
import vn.edu.giadinh.tasksmanagement.models.Task;
import vn.edu.giadinh.tasksmanagement.models.User;
import vn.edu.giadinh.tasksmanagement.utils.Extension;
import vn.edu.giadinh.tasksmanagement.utils.Function;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@ExtensionMethod(Extension.class)
public class BaseServlet extends HttpServlet {
    // Fields:
    protected SearchableDBHandler<User, String> userDBHandler;
    protected DBHandler<Task, Integer> taskDBHandler;

    // Constructors:
    public BaseServlet() {
        super();
        userDBHandler = UserDBHandler.getInstance();
        taskDBHandler = TaskDBHandler.getInstance();
    }

    // Methods:
    @Override
    protected final void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpHandler handler = generateHandler(req, resp);

        safeExecute(
                () -> {
                    try {
                        doGet(handler);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> handler.forward("/")
        );
    }

    @Override
    protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpHandler handler = generateHandler(req, resp);

        safeExecute(
                () -> {
                    try {
                        doPost(handler);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> handler.forward("/")
        );
    }

    @Override
    protected final void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpHandler handler = generateHandler(req, resp);

        safeExecute(
                () -> {
                    try {
                        doPut(handler);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> handler.forward("/")
        );
    }

    @Override
    protected final void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpHandler handler = generateHandler(req, resp);

        safeExecute(
                () -> {
                    try {
                        doDelete(handler);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> handler.forward("/")
        );
    }

    protected void doGet(HttpHandler handler) throws Exception {
        super.doGet(handler.getRequest(), handler.getResponse());
    }

    protected void doPost(HttpHandler handler) throws Exception {
        super.doPost(handler.getRequest(), handler.getResponse());
    }

    protected void doPut(HttpHandler handler) throws Exception {
        super.doPut(handler.getRequest(), handler.getResponse());
    }

    protected void doDelete(HttpHandler handler) throws Exception {
        super.doDelete(handler.getRequest(), handler.getResponse());
    }

    protected User getLoggedInUser(HttpHandler handler) throws SQLException {
        String username = handler.retrieveSession("user", String.class);

        if (username.isNullOrEmpty()) {
            return null;
        }

        return userDBHandler.get(username);
    }

    protected boolean isLoggedIn(HttpHandler handler) throws SQLException {
        return getLoggedInUser(handler) != null;
    }

    protected boolean hasPermission(HttpHandler handler, UserRole... roles) throws SQLException {
        User user = getLoggedInUser(handler);

        if (user == null || user.getRole() == null || roles == null) {
            return false;
        }

        return Arrays.stream(roles)
                .anyMatch(
                        r -> r.equals(user.getRole())
                );
    }

    private void safeExecute(Function function, Function ex) {
        try {
            function.call();
        }
        catch (Exception e) {
            e.printStackTrace();
            ex.call();
        }
    }

    private HttpHandler generateHandler(HttpServletRequest request, HttpServletResponse response) {
        HttpHandler handler = new HttpHandler(request, response);

        safeExecute(
                () -> {
                    try {
                        handler.putRequest("user", getLoggedInUser(handler));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {}
        );

        return handler;
    }

    protected String getViewUrl(String view) {
        return ("/WEB-INF/" + view).replace("//", "/");
    }
}
