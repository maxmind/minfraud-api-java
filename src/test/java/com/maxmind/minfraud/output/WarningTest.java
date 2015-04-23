package com.maxmind.minfraud.output;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class WarningTest extends AbstractOutputTest {

    @Test
    public void testWarningn() throws Exception {
        String code = "INVALID_INPUT";
        String msg = "Input invalid";

        Warning warning = deserialize(
                Warning.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .put("code", code)
                        .put("warning", msg)
                        .startArrayField("input")
                        .add("first")
                        .add("second")
                        .end()
                        .end()
                        .finish()
        );

        assertEquals(code, warning.getCode());
        assertEquals(msg, warning.getWarning());
        assertEquals(
                new ArrayList<>(Arrays.asList("first", "second")),
                warning.getInput()
        );
    }

}