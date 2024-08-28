package vn.edu.giadinh.tasksmanagement.servlet;

import lombok.experimental.ExtensionMethod;
import vn.edu.giadinh.tasksmanagement.utils.Extension;

import java.lang.reflect.Method;
import java.util.Arrays;

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

        // Action null or empty case
        if (action.isNullOrEmpty()) {
            Arrays.stream(methods)
                    .filter(
                            m -> !m.getAnnotationsByType(GetDefaultAction.class).isNullOrEmpty()
                    )
                    .forEach(
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

        // Action not null or empty case
        else {
            Arrays.stream(methods)
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
                    .forEach(
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

        // Get action from handler
        String action = handler.getParameter("action");

        // Action null or empty case
        if (action.isNullOrEmpty()) {
            Arrays.stream(methods)
                    .filter(
                            m -> !m.getAnnotationsByType(PostDetaultAction.class).isNullOrEmpty()
                    )
                    .forEach(
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

        // Action not null or empty case
        else {
            Arrays.stream(methods)
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
                    .forEach(
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
