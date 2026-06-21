package net.bytelyplay.qscwebbackend.utils;

import lombok.Getter;
import net.bytelyplay.qscwebbackend.utils.enums.RefreshTokenOfferType;

public class AuthenticationHelpers {
    @Getter
    private static final AuthenticationHelpers instance =
            new AuthenticationHelpers();
    public byte[] getRefreshTokenFromOffer(RefreshTokenOfferType offer,
                                           String offerValue, String username) {
        return null;
    }
    private AuthenticationHelpers() {}
}
