package vn.edu.giadinh.tasksmanagement.servlet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import vn.edu.giadinh.tasksmanagement.utils.Function;
import vn.edu.giadinh.tasksmanagement.utils.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Getter
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
