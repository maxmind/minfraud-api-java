package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;

public class ReasonTest extends AbstractOutputTest {

    @Test
    public void testReason() throws Exception {
        String code = "ANONYMOUS_IP";
        String msg = "Risk due to IP being an Anonymous IP";

        Reason reason = this.deserialize(
            Reason.class,
            JSON.std
                .composeString()
                .startObject()
                .put("code", code)
                .put("reason", msg)
                .end()
                .finish()
        );

        assertEquals(code, reason.getCode(), "code");
        assertEquals(msg, reason.getReason(), "reason");
    }

}
