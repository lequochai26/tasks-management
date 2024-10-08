package vn.edu.giadinh.tasksmanagement.servlet;

import lombok.experimental.ExtensionMethod;
import vn.edu.giadinh.tasksmanagement.Constants;
import vn.edu.giadinh.tasksmanagement.models.User;
import vn.edu.giadinh.tasksmanagement.utils.Extension;

import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
@ExtensionMethod(Extension.class)
public class LoginServlet extends BaseServlet {
    // Constructors:
    public LoginServlet() {
        super();
    }

    // Methods:
    @Override
    protected void doGet(HttpHandler handler) throws Exception {
        // Logged in case
        if (isLoggedIn(handler)) {
            // Forward back to home page
            handler.addMessage(Constants.MSG_ALREADY_LOGGED_IN);
            showHomeView(handler);

            // Exit the flow
            return;
        }

        // Forward to login.jsp page
        showLoginView(handler);
    }

    @Override
    protected void doPost(HttpHandler handler) throws Exception {
        // Get username and password
        String username = handler.getParameter("username");
        String password = handler.getParameter("password");

        // Username or password null case
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            handler.addMessage(Constants.MSG_INFORMATION_REQUIRED);
            showLoginView(handler);
            return;
        }

        // Get user
        User user = userDBHandler.get(username);

        // User not found case
        user.ifNull(
                () -> {
                    handler.addMessage(Constants.MSG_USER_NOT_FOUND);
                    showLoginView(handler);
                }
        );

        // User found case
        user.ifNotNull(
                () -> {
                    // Incorrect password case
                    if (!password.equals(user.getPassword())) {
                        handler.addMessage(Constants.MSG_LOGIN_INVALID);
                        showLoginView(handler);
                        return;
                    }

                    // Success logged in
                    handler.putSession("user", user.getUsername());
                    handler.redirect(handler.makeRelativePath("/home"));
                }
        );
    }
}
