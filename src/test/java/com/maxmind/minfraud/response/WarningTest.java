package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;

public class WarningTest extends AbstractOutputTest {

    @Test
    public void testWarning() throws Exception {
        String code = "INVALID_INPUT";
        String msg = "Input invalid";
        String pointer = "/first/second";

        Warning warning = this.deserialize(
            Warning.class,
            JSON.std
                .composeString()
                .startObject()
                .put("code", code)
                .put("warning", msg)
                .put("input_pointer", pointer)
                .end()
                .finish()
        );

        assertEquals(code, warning.code(), "code");
        assertEquals(msg, warning.warning(), "warning message");
        assertEquals(pointer, warning.inputPointer(), "input_pointer");
    }

}
