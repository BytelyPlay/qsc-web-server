package net.bytelyplay.qscwebbackend.utils;

import lombok.Getter;
import net.bytelyplay.qscwebbackend.utils.enums.RefreshTokenOfferType;
import net.bytelyplay.qscwebbackend.utils.tokens.RefreshToken;
import net.bytelyplay.qscwebbackend.utils.tokens.TokenHelpers;

import java.util.Optional;

public class AuthenticationHelpers {
    @Getter
    private static final AuthenticationHelpers instance =
            new AuthenticationHelpers();

    public Optional<RefreshToken> getRefreshTokenFromOffer(
            RefreshTokenOfferType offer,
            String offerValue, String username
    ) {
        if (offer.getRefreshTokenOffer()
                .isValid(offerValue, username)) {
            return Optional.of(
                    TokenHelpers
                            .getInstance()
                            .generateRefreshToken(username)
            );
        } else {
            return Optional.empty();
        }
    }

    private AuthenticationHelpers() {}
}
