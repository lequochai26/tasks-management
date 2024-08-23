package vn.edu.giadinh.tasksmanagement.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ArrayUtil {
    public <T> boolean isNullOrEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    public <T> T find (T[] arr, Predicator<T> predicator) {
        if (isNullOrEmpty(arr)) {
            return null;
        }

        for (T t : arr) {
            if (predicator.predicate(t)) {
                return t;
            }
        }

        return null;
    }

    public <T> T findOrThrow(T[] arr, Predicator<T> predicator, RuntimeException e) {
        T target = find(arr, predicator);

        if (target == null) {
            throw e;
        }

        return target;
    }
}
