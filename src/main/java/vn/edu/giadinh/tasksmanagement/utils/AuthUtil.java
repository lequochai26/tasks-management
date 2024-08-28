package vn.edu.giadinh.tasksmanagement.utils;

import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import org.mindrot.jbcrypt.BCrypt;

@UtilityClass
@ExtensionMethod(Extension.class)
public class AuthUtil {
    // Static fields:
    public static final String PEPPER = EnvUtil.getEnv("PEPPER");

    // Utility methods:
    public String encrypt(String password) {
        // Adding pepper to password
        password = addPepper(password);

        // Hashing password
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean check(String password, String encryptedPassword) {
        // Adding pepper to password
        password = addPepper(password);

        // Checking password
        return BCrypt.checkpw(password, encryptedPassword);
    }

    // Private methods:
    private String addPepper(String password) {
        // Adding pepper to password
        if (!PEPPER.isNullOrEmpty()) {
            password += PEPPER;
        }

        return password;
    }
}
