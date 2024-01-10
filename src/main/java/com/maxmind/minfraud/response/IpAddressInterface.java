package com.maxmind.minfraud.response;

/**
 * Interface for IP address risk models.
 */
public interface IpAddressInterface {
    /**
     * @return The risk associated with the IP address.
     */
    Double getRisk();
}
