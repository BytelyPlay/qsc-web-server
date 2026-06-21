package net.bytelyplay.qscwebbackend.utils.offers.refreshtokens;

import lombok.Getter;

import java.sql.Ref;

public class PasswordOffer implements RefreshTokenOffer {
    @Getter
    private static final PasswordOffer instance = new PasswordOffer();

    @Override
    public boolean isValid(String offerValue, String username) {
        return false;
    }
    private PasswordOffer() {}
}
