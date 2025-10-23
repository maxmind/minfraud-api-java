package com.maxmind.minfraud.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import org.junit.jupiter.api.Test;

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
            .build().inputs();

        assertEquals("test string", inputs.get("string_input_1"));
        assertEquals(19, inputs.get("int_input"));
        assertEquals(12L, inputs.get("long_input"));
        assertEquals(3.2f, inputs.get("float_input"));
        assertEquals(32.123d, inputs.get("double_input"));
        assertEquals(true, inputs.get("bool_input"));
    }

    @Test
    public void testInvalidKey() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new CustomInputs.Builder().put("InvalidKey", 1)
        );
    }

    @Test
    public void testStringThatIsTooLong() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new CustomInputs.Builder().put("string",
                new String(new char[256]).replace('\0', 'x'))
        );
    }

    @Test
    public void testStringWithNewLine() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new CustomInputs.Builder().put("string", "test\n")
        );
    }

    @Test
    public void testTooLargeLong() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new CustomInputs.Builder().put("long", 10_000_000_000_000L)
        );
    }

    @Test
    public void testTooSmallLong() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new CustomInputs.Builder().put("long", -10_000_000_000_000L)
        );
    }

    @Test
    public void testTooLargeDouble() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new CustomInputs.Builder().put("double", 1e13)
        );
    }
}
