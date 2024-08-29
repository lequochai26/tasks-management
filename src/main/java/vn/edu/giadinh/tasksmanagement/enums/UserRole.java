package vn.edu.giadinh.tasksmanagement.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserRole {
    ADMIN,
    DEVELOPER,
    QUALITY_CONTROL;

    private static Map<String, UserRole> roleMap;

    public static UserRole from(String name) {
        if (getRoleMap() == null || !roleMap.containsKey(name)) {
            return null;
        }

        return roleMap.get(name);
    }

    private static Map<String, UserRole> getRoleMap() {
        if (roleMap == null) {
            roleMap = new HashMap<>();
            for (UserRole role : UserRole.values()) {
                roleMap.put(role.name(), role);
            }
        }

        return roleMap;
    }
}
