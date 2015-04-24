package com.maxmind.minfraud.response;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.InjectableValues.Std;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;

public abstract class AbstractOutputTest {

    protected <T> T deserialize(Class<T> cls, String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InjectableValues inject = new Std().addValue(
                "locales", Collections.singletonList("en"));
        return mapper.reader(cls).with(inject).readValue(json);
    }

}
