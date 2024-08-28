package vn.edu.giadinh.tasksmanagement.servlet;

import lombok.experimental.ExtensionMethod;
import vn.edu.giadinh.tasksmanagement.Constants;
import vn.edu.giadinh.tasksmanagement.models.User;
import vn.edu.giadinh.tasksmanagement.utils.Extension;

import javax.servlet.annotation.WebServlet;

@WebServlet("/update-user")
@ExtensionMethod(Extension.class)
public class UpdateUserServlet extends BaseServlet {
    // Constructors:
    public UpdateUserServlet() {
        super();
    }

    // Methods:
    @Override
    protected void doPost(HttpHandler handler) throws Exception {
        // Get logged in user
        User user = getLoggedInUser(handler);

        // Not logged in case
        if (user == null) {
            // Add message
            handler.addMessage(Constants.MSG_USER_NOT_LOGGED_IN);

            // Show login view
            showLoginView(handler);

            // Exit flow
            return;
        }

        // Full name updating
        String fullName = handler.getParameter("fullName");
        if (!fullName.isNullOrEmpty()) {
            user.setFullName(fullName);
        }

        // Password updating
        String password = handler.getParameter("password");
        if (!password.isNullOrEmpty()) {
            user.setPassword(password);
        }

        // Update user
        userDBHandler.update(user);

        // Add message
        handler.addMessage(Constants.MSG_UPDATED_SUCCESSFULLY);

        // Forward back to current view or home view
        String referer = handler.getHeader("Referer");
        if (referer.isNullOrEmpty()) {
            visitHomeView(handler);
        }
        else {
            handler.redirect(referer);
        }
    }
}
