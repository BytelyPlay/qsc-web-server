package net.bytelyplay.qscwebbackend.utils.enums;

import lombok.Getter;
import net.bytelyplay.qscwebbackend.utils.offers.refreshtokens.PasswordOffer;
import net.bytelyplay.qscwebbackend.utils.offers.refreshtokens.RefreshTokenOffer;

import java.util.Optional;

public enum RefreshTokenOfferType {
    PASSWORD("password", PasswordOffer.getInstance());

    @Getter
    private final String offerString;

    @Getter
    private final RefreshTokenOffer<?> refreshTokenOffer;

    RefreshTokenOfferType(String offerString, RefreshTokenOffer<?> offerInstance) {
       this.offerString = offerString;
       this.refreshTokenOffer = offerInstance;
    }
    public static Optional<RefreshTokenOfferType> getOfferFromString(String offerString) {
        for (RefreshTokenOfferType offer : RefreshTokenOfferType.values())
            if (offerString.equals(offer.getOfferString()))
                return Optional.of(offer);
        return Optional.empty();
    }
}
