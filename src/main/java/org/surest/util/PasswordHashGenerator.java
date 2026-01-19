package org.surest.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHashGenerator {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private PasswordHashGenerator() {
    }

    public static String hashPassword(String rawPassword) {
        return PASSWORD_ENCODER.encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String hashedPassword) {
        return PASSWORD_ENCODER.matches(rawPassword, hashedPassword);
    }
}
