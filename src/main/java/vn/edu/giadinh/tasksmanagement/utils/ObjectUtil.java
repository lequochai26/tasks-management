package vn.edu.giadinh.tasksmanagement.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjectUtil {
    public void ifNotNull(Object target, Function function) {
        if (target != null) {
            function.call();
        }
    }

    public void ifNull(Object target, Function function) {
        if (target == null) {
            function.call();
        }
    }

    public void ifNullThrow(Object target, RuntimeException e) {
        if (target == null) {
            throw e;
        }
    }
}
