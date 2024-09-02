package vn.edu.giadinh.tasksmanagement.servlet;

import lombok.experimental.ExtensionMethod;
import vn.edu.giadinh.tasksmanagement.utils.Extension;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExtensionMethod(Extension.class)
public class ActionBaseServlet extends BaseServlet {

    // Constructors:
    public ActionBaseServlet(){
        super();
    }

    // Methods:
    @Override
    protected void doGet(HttpHandler handler) throws Exception {
        // Get this object's class
        Class type = this.getClass();

        // Get all methods
        Method[] methods = type.getDeclaredMethods();

        // Exit if methods null or empty
        if (methods.isNullOrEmpty()) {
            super.doGet(handler);
            return;
        }

        // Get action from handler
        String action = handler.getParameter("action");

        // Get default method
        List<Method> defaultMethod = Arrays.stream(methods)
                .filter(
                        m -> !m.getAnnotationsByType(GetDefaultAction.class).isNullOrEmpty()
                )
                .collect(Collectors.toList());
        List<Method> actionMethods = null;

        // Action not null and not empty
        if (!action.isNullOrEmpty()) {
            actionMethods = Arrays.stream(methods)
                    .filter(
                            m -> {
                                GetAction[] actions = m.getAnnotationsByType(GetAction.class);

                                if (actions.isNullOrEmpty()) {
                                    return false;
                                }

                                return Arrays.stream(actions)
                                        .filter(
                                                a -> !a.value().isNullOrEmpty()
                                        )
                                        .anyMatch(
                                                a -> a.value().equals(action)
                                        );
                            }
                    )
                    .collect(Collectors.toList());
        }

        // Methods execution
        if (!actionMethods.isNullOrEmpty()) {
            actionMethods.forEach(
                    m -> {
                        try {
                            m.invoke(this, handler);
                        }
                        catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
        else {
            defaultMethod.forEach(
                    m -> {
                        try {
                            m.invoke(this, handler);
                        }
                        catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpHandler handler) throws Exception {
        // Get this object's class
        Class type = this.getClass();

        // Get all methods
        Method[] methods = type.getDeclaredMethods();

        // Exit if methods null or empty
        if (methods.isNullOrEmpty()) {
            super.doPost(handler);
            return;
        }

        // Get methods
        List<Method> defaultMethod = Arrays.stream(methods)
                .filter(
                        m -> !m.getAnnotationsByType(PostDetaultAction.class).isNullOrEmpty()
                )
                .collect(Collectors.toList());

        List<Method> actionMethods = null;

        // Get action from handler
        String action = handler.getParameter("action");

        // Action not null and not empty
        if (!action.isNullOrEmpty()) {
            actionMethods = Arrays.stream(methods)
                    .filter(
                            m -> {
                                PostAction[] actions = m.getAnnotationsByType(PostAction.class);

                                if (actions.isNullOrEmpty()) {
                                    return false;
                                }

                                return Arrays.stream(actions)
                                        .filter(
                                                a -> !a.value().isNullOrEmpty()
                                        )
                                        .anyMatch(
                                                a -> a.value().equals(action)
                                        );
                            }
                    )
                    .collect(Collectors.toList());
        }

        // Methods execution
        if (!actionMethods.isNullOrEmpty()) {
            actionMethods.forEach(
                    m -> {
                        try {
                            m.invoke(this, handler);
                        }
                        catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
        else {
            defaultMethod.forEach(
                    m -> {
                        try {
                            m.invoke(this, handler);
                        }
                        catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }
}
