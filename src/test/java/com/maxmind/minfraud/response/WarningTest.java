package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

        assertEquals("code", code, warning.getCode());
        assertEquals("warning message", msg, warning.getWarning());
        assertEquals("input_pointer", pointer, warning.getInputPointer());
    }

}
