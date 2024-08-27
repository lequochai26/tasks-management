package vn.edu.giadinh.tasksmanagement.servlet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.ExtensionMethod;
import vn.edu.giadinh.tasksmanagement.utils.Extension;
import vn.edu.giadinh.tasksmanagement.utils.Function;
import vn.edu.giadinh.tasksmanagement.utils.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@ExtensionMethod(Extension.class)
public class HttpHandler {
    // Static fields:
    public static final String LOGGED_IN_USER_KEY = "user";

    // Fields:
    private HttpServletRequest request;
    private HttpServletResponse response;

    // Methods:
    public Object retrieveSession(String key) {
        return safeSessionSupply(
                () -> request.getSession()
                        .getAttribute(key)
        );
    }

    public <T> T retrieveSession(String key, Class<T> returnType) {
        Object value = retrieveSession(key);

        if (!returnType.isInstance(value)) {
            return null;
        }

        return returnType.cast(value);
    }

    public void putSession(String key, Object value) {
        safeSessionExecute(
                () -> request.getSession()
                        .setAttribute(key, value)
        );
    }

    public void forward(String path) {
        safeExecute(
                () -> {
                    try {
                        request.getRequestDispatcher(path)
                                .forward(request, response);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    public void redirect(String url) {
        safeExecute(
                () -> {
                    try {
                        response.sendRedirect(url);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    public void putRequest(String key, Object value) {
        safeExecute(
                () -> request.setAttribute(key, value)
        );
    }

    public String getParameter(String key) {
        return safeSupply(
                () -> request.getParameter(key)
        );
    }

    public String getContextPath() {
        return safeSupply(
                () -> request.getContextPath()
        );
    }

    public String makeRelativePath(String path) {
        return safeSupply(
                () -> getContextPath() + path
        );
    }

    public void addMessage(String message) {
        safeExecute(
                () -> {
                    List<String> messages = retrieveRequest("messages", List.class);

                    if (messages.isNullOrEmpty()) {
                        messages = new ArrayList<>();
                        putRequest("messages", messages);
                    }

                    messages.add(message);
                }
        );
    }

    public Object retrieveRequest(String key) {
        return safeSupply(
                () -> request.getAttribute(key)
        );
    }

    public <T> T retrieveRequest(String key, Class<T> returnType) {
        return safeSupply(
                () -> returnType.cast(retrieveRequest(key))
        );
    }

    private void safeExecute(Function function) {
        if (function == null || request == null || response == null) {
            return;
        }

        function.call();
    }

    private <T> T safeSupply(Supplier<T> supplier) {
        if (supplier == null || request == null || response == null) {
            return null;
        }

        return supplier.supply();
    }

    private void safeSessionExecute(Function function) {
        safeExecute(
                () -> {
                    if (request.getSession() == null) {
                        return;
                    }

                    function.call();
                }
        );
    }

    private <T> T safeSessionSupply(Supplier<T> supplier) {
        return safeSupply(
                () -> {
                    if (request.getSession() == null) {
                        return null;
                    }

                    return supplier.supply();
                }
        );
    }
}
