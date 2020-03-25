package com.maxmind.minfraud;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public abstract class AbstractModel {

    /**
     * @return JSON representation of this object.
     * @throws IOException if there is an error serializing the object to JSON.
     */
    public final String toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.disable(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS);
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.writeValueAsString(this);
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