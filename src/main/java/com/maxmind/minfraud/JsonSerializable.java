package com.maxmind.minfraud;

import java.io.IOException;

/**
 * Interface for classes that can be serialized to JSON.
 * Provides default implementation for toJson() method.
 */
public interface JsonSerializable {

    /**
     * @return JSON representation of this object.
     * @throws IOException if there is an error serializing the object to JSON.
     */
    default String toJson() throws IOException {
        return Mapper.get().writeValueAsString(this);
    }
}
