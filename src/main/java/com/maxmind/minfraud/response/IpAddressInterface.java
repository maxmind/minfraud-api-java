package com.maxmind.minfraud.response;

/**
 * Interface for IP address risk models.
 */
public interface IpAddressInterface {
    /**
     * @return The risk associated with the IP address.
     */
    Double risk();

    /**
     * @return The risk associated with the IP address.
     * @deprecated Use {@link #risk()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    default Double getRisk() {
        return risk();
    }
}
