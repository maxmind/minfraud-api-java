package com.maxmind.minfraud.response;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.InjectableValues.Std;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.Collections;

public abstract class AbstractOutputTest {

    <T> T deserialize(Class<T> cls, String json) throws IOException {
        ObjectMapper         mapper = new ObjectMapper();
        mapper.disable(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS);
        mapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.registerModule(new JavaTimeModule());
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        InjectableValues inject = new Std().addValue(
                "locales", Collections.singletonList("en"));
        return mapper.readerFor(cls).with(inject).readValue(json);
    }
}
