package net.bytelyplay.qscwebbackend.utils.tokens;

import lombok.Getter;

public abstract class Token {
    public static final int TOKEN_LENGTH = 32;

    @Getter
    private final byte[] token;

    public Token(byte[] token) {
        if (token.length != TOKEN_LENGTH)
            throw new IllegalArgumentException(
                    "Token not " + TOKEN_LENGTH + " bytes long."
            );
        this.token = token;
    }

    public String getTokenAsString() {
        StringBuilder sb = new StringBuilder();

        for (byte b : token)
            sb.append(String.format("%02X", b));
        return sb.toString();
    }
}
