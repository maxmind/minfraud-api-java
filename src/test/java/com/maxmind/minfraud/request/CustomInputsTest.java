package com.maxmind.minfraud.request;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CustomInputsTest {
    @Test
    public void TestPuttingTypes() {
        Map<String, Object> inputs = new CustomInputs.Builder()
                .put("string_input_1", "test string")
                .put("int_input", 19)
                .put("long_input", 12L)
                .put("float_input", 3.2f)
                .put("double_input", 32.123d)
                .put("bool_input", true)
                .build().getInputs();

        assertEquals("test string", inputs.get("string_input_1"));
        assertEquals(19, inputs.get("int_input"));
        assertEquals(12L, inputs.get("long_input"));
        assertEquals(3.2f, inputs.get("float_input"));
        assertEquals(32.123d, inputs.get("double_input"));
        assertEquals(true, inputs.get("bool_input"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidKey() throws Exception {
        new CustomInputs.Builder().put("InvalidKey", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStringThatIsTooLong() throws Exception {
        new CustomInputs.Builder().put("string",
                new String(new char[256]).replace('\0', 'x'));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStringWithNewLine() throws Exception {
        new CustomInputs.Builder().put("string", "test\n");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooLargeLong() throws Exception {
        new CustomInputs.Builder().put("long", 1L << 53);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooSmallLong() throws Exception {
        new CustomInputs.Builder().put("long", -(1L << 53));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooLargeDouble() throws Exception {
        new CustomInputs.Builder().put("double", (double) (1L << 53));
    }
}