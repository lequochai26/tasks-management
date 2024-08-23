package vn.edu.giadinh.tasksmanagement.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {
    public boolean isNullOrEmpty(String target) {
        if (target == null) {
            return true;
        }

        return target.isEmpty();
    }
}
