package vn.edu.giadinh.tasksmanagement.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;

@UtilityClass
public class Extension {
    public void ifNotNull(Object target, Function function) {
        ObjectUtil.ifNotNull(target, function);
    }

    public void ifNull(Object target, Function function) {
        ObjectUtil.ifNull(target, function);
    }

    public void ifNullThrow(Object target, RuntimeException e) {
        ObjectUtil.ifNullThrow(target, e);
    }

    public boolean isNullOrEmpty(Collection<?> target) {
        return CollectionUtil.isNullOrEmpty(target);
    }

    public <T> T find(Collection<T> collection, Predicator<T> predicator) {
        return CollectionUtil.find(collection, predicator);
    }

    public <T> T findOrThrow(Collection<T> collection, Predicator<T> predicator, RuntimeException e) {
        return CollectionUtil.findOrThrow(collection, predicator, e);
    }

    public boolean isNullOrEmpty(String target) {
        return StringUtil.isNullOrEmpty(target);
    }

    public <T> boolean isNullOrEmpty(T[] target) {
        return ArrayUtil.isNullOrEmpty(target);
    }

    public <T> T find(T[] arr, Predicator<T> predicator) {
        return ArrayUtil.find(arr, predicator);
    }

    public <T> T findOrThrow(T[] arr, Predicator<T> predicator, RuntimeException e) {
        return ArrayUtil.findOrThrow(arr, predicator, e);
    }

    public boolean canConvertToInt(String target) {
        return StringUtil.canConvertToInt(target);
    }

    public <T> T ifNotNullSupply(Object target, Supplier<T> supplier) {
        return ObjectUtil.ifNotNullSupply(target, supplier);
    }
}
