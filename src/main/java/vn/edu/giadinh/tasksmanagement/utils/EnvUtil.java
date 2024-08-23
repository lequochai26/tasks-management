package vn.edu.giadinh.tasksmanagement.utils;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class EnvUtil {
    public String getEnv(String key) {
        if (key == null) {
            return null;
        }

        return System.getenv(key);
    }

    public Map<String, String> env() {
        return System.getenv();
    }
}
