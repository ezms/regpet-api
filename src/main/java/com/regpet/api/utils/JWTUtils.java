package com.regpet.api.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class JWTUtils {
    private static final int SECRET_LENGTH = 32;

    public static String generateClientSecret() {
        SecureRandom random = new SecureRandom();
        byte[] secret = new byte[SECRET_LENGTH];
        random.nextBytes(secret);
        return Base64.getEncoder().encodeToString(secret);
    }
}
