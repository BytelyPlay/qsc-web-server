package net.bytelyplay.qscwebbackend.utils.offers;

public interface Offer {
    boolean isValid(String offerValue, String username);
}
