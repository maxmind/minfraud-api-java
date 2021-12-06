package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.InjectableValues.Std;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.Collections;

public abstract class AbstractOutputTest {

    <T> T deserialize(Class<T> cls, String json) throws IOException {
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .defaultDateFormat(new StdDateFormat().withColonInTimeZone(true))
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .disable(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .serializationInclusion(JsonInclude.Include.NON_EMPTY)
                .build();
        InjectableValues inject = new Std().addValue(
                "locales", Collections.singletonList("en"));
        return mapper.readerFor(cls).with(inject).readValue(json);
    }
}
