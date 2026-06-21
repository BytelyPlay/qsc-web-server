package net.bytelyplay.qscwebbackend.utils.offers;

public interface Offer<T> {
    boolean isValid(T offerValue, String username);
}
