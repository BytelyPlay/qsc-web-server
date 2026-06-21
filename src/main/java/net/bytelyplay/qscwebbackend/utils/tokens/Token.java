package net.bytelyplay.qscwebbackend.utils.tokens;

import lombok.Getter;

public abstract class Token {
    @Getter
    private final byte[] token;

    public Token(byte[] token) {
        this.token = token;
    }

    public String getTokenAsString() {
        StringBuilder sb = new StringBuilder();

        for (byte b : token)
            sb.append(String.format("%02X", b));
        return sb.toString();
    }
}
