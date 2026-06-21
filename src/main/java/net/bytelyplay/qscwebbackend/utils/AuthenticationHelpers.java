package net.bytelyplay.qscwebbackend.utils;

import lombok.Getter;

public class AuthenticationHelpers {
    @Getter
    private static final AuthenticationHelpers instance =
            new AuthenticationHelpers();

    private AuthenticationHelpers() {}
}
