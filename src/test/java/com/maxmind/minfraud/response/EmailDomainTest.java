package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.jr.ob.JSON;
import com.maxmind.minfraud.response.EmailDomain.Classification;
import com.maxmind.minfraud.response.EmailDomainVisit.Status;
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

        assertEquals(LocalDate.parse("2014-02-03"), domain.firstSeen());
    }

    @Test
    public void testEmailDomainWithAllFields() throws Exception {
        EmailDomain domain = this.deserialize(
            EmailDomain.class,
            JSON.std
                .composeString()
                .startObject()
                .put("classification", "education")
                .put("first_seen", "2019-01-15")
                .put("risk", 15.5)
                .startObjectField("visit")
                .put("has_redirect", true)
                .put("last_visited_on", "2024-11-15")
                .put("status", "live")
                .end()
                .put("volume", 630000.0)
                .end()
                .finish()
        );

        assertEquals(Classification.EDUCATION, domain.classification());
        assertEquals("education", domain.classification().toString());
        assertEquals(LocalDate.parse("2019-01-15"), domain.firstSeen());
        assertEquals(15.5, domain.risk());
        assertEquals(630000.0, domain.volume());

        assertNotNull(domain.visit());
        assertTrue(domain.visit().hasRedirect());
        assertEquals(LocalDate.parse("2024-11-15"), domain.visit().lastVisitedOn());
        assertEquals(Status.LIVE, domain.visit().status());
    }

    @Test
    public void testEmailDomainWithBusinessClassification() throws Exception {
        EmailDomain domain = this.deserialize(
            EmailDomain.class,
            JSON.std
                .composeString()
                .startObject()
                .put("classification", "business")
                .put("risk", 5.0)
                .end()
                .finish()
        );

        assertEquals(Classification.BUSINESS, domain.classification());
        assertEquals("business", domain.classification().toString());
        assertEquals(5.0, domain.risk());
    }

    @Test
    public void testEmailDomainWithGovernmentClassification() throws Exception {
        EmailDomain domain = this.deserialize(
            EmailDomain.class,
            JSON.std
                .composeString()
                .startObject()
                .put("classification", "government")
                .end()
                .finish()
        );

        assertEquals(Classification.GOVERNMENT, domain.classification());
        assertEquals("government", domain.classification().toString());
    }

    @Test
    public void testEmailDomainWithIspEmailClassification() throws Exception {
        EmailDomain domain = this.deserialize(
            EmailDomain.class,
            JSON.std
                .composeString()
                .startObject()
                .put("classification", "isp_email")
                .put("volume", 500000.5)
                .end()
                .finish()
        );

        assertEquals(Classification.ISP_EMAIL, domain.classification());
        assertEquals("isp_email", domain.classification().toString());
        assertEquals(500000.5, domain.volume());
    }

    @Test
    public void testEmailDomainWithUnknownClassification() throws Exception {
        EmailDomain domain = this.deserialize(
            EmailDomain.class,
            JSON.std
                .composeString()
                .startObject()
                .put("classification", "future_new_classification")
                .put("risk", 20.0)
                .end()
                .finish()
        );

        assertNull(domain.classification());
        assertEquals(20.0, domain.risk());
    }

    @Test
    public void testEmailDomainWithVisitOnly() throws Exception {
        EmailDomain domain = this.deserialize(
            EmailDomain.class,
            JSON.std
                .composeString()
                .startObject()
                .startObjectField("visit")
                .put("status", "parked")
                .put("last_visited_on", "2024-10-20")
                .end()
                .end()
                .finish()
        );

        assertNotNull(domain.visit());
        assertEquals(Status.PARKED, domain.visit().status());
        assertEquals(LocalDate.parse("2024-10-20"), domain.visit().lastVisitedOn());
    }

    @Test
    public void testEmailDomainEmpty() throws Exception {
        EmailDomain domain = this.deserialize(
            EmailDomain.class,
            JSON.std
                .composeString()
                .startObject()
                .end()
                .finish()
        );

        assertNull(domain.classification());
        assertNull(domain.firstSeen());
        assertNull(domain.risk());
        assertNotNull(domain.visit());
        assertNull(domain.volume());
    }
}
