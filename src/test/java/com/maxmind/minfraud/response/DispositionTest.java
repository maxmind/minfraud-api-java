package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DispositionTest extends AbstractOutputTest {

    @Test
    public void testDisposition() throws Exception {
        Disposition disposition = this.deserialize(
                Disposition.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .put("action", "accept")
                        .put("reason", "default")
                        .put("rule_label", "the label")
                        .end()
                        .finish()
        );

        assertEquals("accept", disposition.getAction());
        assertEquals("default", disposition.getReason());
        assertEquals("the label", disposition.getRuleLabel());
    }
}
