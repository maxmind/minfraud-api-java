package com.maxmind.minfraud;

import java.io.IOException;

/**
 * This {@code AbstractModel} is the base class for all model classes.
 */
public abstract class AbstractModel {
    /**
     * @return JSON representation of this object.
     * @throws IOException if there is an error serializing the object to JSON.
     */
    public final String toJson() throws IOException {
        return Mapper.get().writeValueAsString(this);
    }

    @Override
    public String toString() {
        // This exception should never happen. If it does happen, we did
        // something wrong.
        try {
            return getClass().getName() + " [ " + toJson() + " ]";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}