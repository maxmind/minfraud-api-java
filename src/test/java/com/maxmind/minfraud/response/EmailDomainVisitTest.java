package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.jr.ob.JSON;
import com.maxmind.minfraud.response.EmailDomainVisit.Status;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class EmailDomainVisitTest extends AbstractOutputTest {

    @Test
    public void testEmailDomainVisitWithAllFields() throws Exception {
        EmailDomainVisit visit = this.deserialize(
            EmailDomainVisit.class,
            JSON.std
                .composeString()
                .startObject()
                .put("has_redirect", true)
                .put("last_visited_on", "2024-11-15")
                .put("status", "live")
                .end()
                .finish()
        );

        assertTrue(visit.hasRedirect());
        assertEquals(LocalDate.parse("2024-11-15"), visit.lastVisitedOn());
        assertEquals(Status.LIVE, visit.status());
        assertEquals("live", visit.status().toString());
    }

    @Test
    public void testEmailDomainVisitWithMinimalFields() throws Exception {
        EmailDomainVisit visit = this.deserialize(
            EmailDomainVisit.class,
            JSON.std
                .composeString()
                .startObject()
                .put("status", "parked")
                .end()
                .finish()
        );

        assertNull(visit.hasRedirect());
        assertNull(visit.lastVisitedOn());
        assertEquals(Status.PARKED, visit.status());
        assertEquals("parked", visit.status().toString());
    }

    @Test
    public void testEmailDomainVisitWithDnsError() throws Exception {
        EmailDomainVisit visit = this.deserialize(
            EmailDomainVisit.class,
            JSON.std
                .composeString()
                .startObject()
                .put("status", "dns_error")
                .put("last_visited_on", "2024-10-01")
                .end()
                .finish()
        );

        assertEquals(Status.DNS_ERROR, visit.status());
        assertEquals("dns_error", visit.status().toString());
        assertEquals(LocalDate.parse("2024-10-01"), visit.lastVisitedOn());
    }

    @Test
    public void testEmailDomainVisitWithNetworkError() throws Exception {
        EmailDomainVisit visit = this.deserialize(
            EmailDomainVisit.class,
            JSON.std
                .composeString()
                .startObject()
                .put("status", "network_error")
                .end()
                .finish()
        );

        assertEquals(Status.NETWORK_ERROR, visit.status());
        assertEquals("network_error", visit.status().toString());
    }

    @Test
    public void testEmailDomainVisitWithHttpError() throws Exception {
        EmailDomainVisit visit = this.deserialize(
            EmailDomainVisit.class,
            JSON.std
                .composeString()
                .startObject()
                .put("status", "http_error")
                .end()
                .finish()
        );

        assertEquals(Status.HTTP_ERROR, visit.status());
        assertEquals("http_error", visit.status().toString());
    }

    @Test
    public void testEmailDomainVisitWithPreDevelopment() throws Exception {
        EmailDomainVisit visit = this.deserialize(
            EmailDomainVisit.class,
            JSON.std
                .composeString()
                .startObject()
                .put("status", "pre_development")
                .end()
                .finish()
        );

        assertEquals(Status.PRE_DEVELOPMENT, visit.status());
        assertEquals("pre_development", visit.status().toString());
    }

    @Test
    public void testEmailDomainVisitWithUnknownStatus() throws Exception {
        EmailDomainVisit visit = this.deserialize(
            EmailDomainVisit.class,
            JSON.std
                .composeString()
                .startObject()
                .put("status", "future_new_status")
                .put("last_visited_on", "2024-11-01")
                .end()
                .finish()
        );

        assertNull(visit.status());
        assertEquals(LocalDate.parse("2024-11-01"), visit.lastVisitedOn());
    }

    @Test
    public void testEmailDomainVisitEmpty() throws Exception {
        EmailDomainVisit visit = this.deserialize(
            EmailDomainVisit.class,
            JSON.std
                .composeString()
                .startObject()
                .end()
                .finish()
        );

        assertNull(visit.hasRedirect());
        assertNull(visit.lastVisitedOn());
        assertNull(visit.status());
    }
}
