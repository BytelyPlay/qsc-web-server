package net.bytelyplay.qscwebbackend.utils.tokens;

import lombok.Getter;

import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;

public class TokenHelpers {
    /**
     * Temporary TODO: Replace with MySQL + Redis
     */
    private static final ConcurrentHashMap<Token, String> TOKEN_TO_USERNAME =
            new ConcurrentHashMap<>();

    private static final SecureRandom SECURE_RAND =
            new SecureRandom();

    @Getter
    private static final TokenHelpers instance =
            new TokenHelpers();

    public RefreshToken generateRefreshToken(String username) {
        byte[] tokenBytes = new byte[32];
        SECURE_RAND.nextBytes(tokenBytes);

        RefreshToken token = new RefreshToken(tokenBytes);
        TOKEN_TO_USERNAME.put(token, username);

        return token;
    }
}
