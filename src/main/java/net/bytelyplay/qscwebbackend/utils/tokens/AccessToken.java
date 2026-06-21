package net.bytelyplay.qscwebbackend.utils.tokens;

import lombok.Getter;

public class AccessToken extends Token {
    public AccessToken(byte[] token) {
        super(token);
    }
}
