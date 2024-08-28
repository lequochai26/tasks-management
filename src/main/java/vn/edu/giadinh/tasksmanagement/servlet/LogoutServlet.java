package vn.edu.giadinh.tasksmanagement.servlet;

import vn.edu.giadinh.tasksmanagement.Constants;

import javax.servlet.annotation.WebServlet;

@WebServlet("/logout")
public class LogoutServlet extends LoginServlet {
    // Constructors:
    public LogoutServlet() {
        super();
    }

    // Methods:
    @Override
    protected void doGet(HttpHandler handler) throws Exception {
        // Not logged in case
        if (!isLoggedIn(handler)) {
            // Add message
            handler.addMessage(Constants.MSG_USER_NOT_LOGGED_IN);
        }
        // Logged in case
        else {
            // Remove user from session
            handler.removeSession("user");
        }

        // Show login view
        showLoginView(handler);
    }
}
