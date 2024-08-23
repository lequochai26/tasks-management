package vn.edu.giadinh.tasksmanagement.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;

@UtilityClass
public class CollectionUtil {
    public boolean isNullOrEmpty(Collection<?> target) {
        return target == null || target.isEmpty();
    }

    public <T> T find(Collection<T> collection, Predicator<T> predicator) {
        if (isNullOrEmpty(collection)) {
            return null;
        }

        return collection.stream()
                .filter(predicator::predicate)
                .findFirst()
                .orElse(null);
    }

    public <T> T findOrThrow(Collection<T> collection, Predicator<T> predicator, RuntimeException e) {
        if (isNullOrEmpty(collection)) {
            throw e;
        }

        return collection.stream()
                .filter(predicator::predicate)
                .findFirst()
                .orElseThrow(() -> e);
    }
}
