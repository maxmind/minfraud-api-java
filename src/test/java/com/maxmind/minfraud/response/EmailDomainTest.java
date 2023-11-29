package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.jr.ob.JSON;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class EmailDomainTest extends AbstractOutputTest {

    @Test
    public void testEmailDomain() throws Exception {
        EmailDomain domain = this.deserialize(
            EmailDomain.class,
            JSON.std
                .composeString()
                .startObject()
                .put("first_seen", "2014-02-03")
                .end()
                .finish()
        );

        assertEquals(LocalDate.parse("2014-02-03"), domain.getFirstSeen());
    }
}
